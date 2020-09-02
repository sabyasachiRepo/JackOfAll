package com.tools.jackofall

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.tools.core.BaseFragment
import com.tools.core.FeatureRegistryImpl
import com.tools.jackofall.databinding.FragmentMainBinding

class MainFragment
    : BaseFragment<FragmentMainBinding, MainViewModel>() {

    private lateinit var manager: SplitInstallManager
    private val moduleNews by lazy { getString(R.string.title_news) }
    private val packageName = "com.tools.news"
    private val newsArticleClassname = "$packageName.NewsArticleListActivity"
    private val clickListener by lazy {
        View.OnClickListener {
            when (it.id) {
                R.id.btn_news -> loadAndLaunchModule(moduleNews)
            }
        }
    }

    /** Listener used to handle changes in state for install requests. */
    private val listener = SplitInstallStateUpdatedListener { state ->
        val multiInstall = state.moduleNames().size > 1
        val names = state.moduleNames().joinToString(" - ")
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                //  In order to see this, the application has to be uploaded to the Play Store.
                displayLoadingState(state, "Downloading $names")
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                /*
                  This may occur when attempting to download a sufficiently large module.
                  In order to see this, the application has to be uploaded to the Play Store.
                  Then features can be requested until the confirmation path is triggered.
                 */
                // startIntentSender(state.resolutionIntent()?.intentSender, null, 0, 0, 0)
            }
            SplitInstallSessionStatus.INSTALLED -> {
                onSuccessfulLoad(names, launch = !multiInstall)
            }

            SplitInstallSessionStatus.INSTALLING -> displayLoadingState(state, "Installing $names")
            SplitInstallSessionStatus.FAILED -> {
                toastAndLog("Error: ${state.errorCode()} for module ${state.moduleNames()}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manager = SplitInstallManagerFactory.create(activity)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListener(binding.root)
        addStaticFeatures(binding.entryPointContainer)
    }


    override fun onResume() {
        // Listener can be registered even without directly triggering a download.
        manager.registerListener(listener)
        setTitle("Home")
        hideNavigationIcon()
        super.onResume()
    }

    override fun onPause() {
        // Make sure to dispose of the listener once it's no longer needed.
        manager.unregisterListener(listener)
        super.onPause()
    }

    /** Set all click listeners required for the buttons on the UI. */
    private fun setupClickListener(view: View) {

        setClickListener(view, R.id.btn_news, clickListener)

    }

    private fun setClickListener(view: View, id: Int, listener: View.OnClickListener) {
        view.findViewById<View>(id).setOnClickListener(listener)
    }

    private fun addStaticFeatures(view: View) {
        if (view is LinearLayout) {
            for (feature in FeatureRegistryImpl.getInstance().staticFeatureList) {
                view.addView(feature.getFeatureEntryPoint(view))
            }
        }


    }

    companion object {
        @JvmStatic
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    /** Display a loading state to the user. */
    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
        displayProgress()

        binding.progressBar.max = state.totalBytesToDownload().toInt()
        binding.progressBar.progress = state.bytesDownloaded().toInt()

        updateProgressMessage(message)
    }

    private fun updateProgressMessage(message: String) {
        if (binding.progress.visibility != View.VISIBLE) displayProgress()
        binding.progressText.text = message
    }

    /** Display progress bar and text. */
    private fun displayProgress() {
        binding.progress.visibility = View.VISIBLE
        binding.buttons.visibility = View.GONE
    }

    /** Display buttons to accept user input. */
    private fun displayButtons() {
        binding.progress.visibility = View.GONE
        binding.buttons.visibility = View.VISIBLE
    }

    /**
     * Load a feature by module name.
     * @param name The name of the feature module to load.
     */
    private fun loadAndLaunchModule(name: String) {
        updateProgressMessage("Loading module $name")
        // Skip loading if the module already is installed. Perform success action directly.
        if (manager.installedModules.contains(name)) {
            updateProgressMessage("Already installed")
            onSuccessfulLoad(name, launch = true)
            return
        }

        // Create request to install a feature module by name.
        val request = SplitInstallRequest.newBuilder()
                .addModule(name)
                .build()

        // Load and install the requested feature module.
        manager.startInstall(request)

        updateProgressMessage("Starting install for $name")
    }

    /**
     * Define what to do once a feature module is loaded successfully.
     * @param moduleName The name of the successfully loaded module.
     * @param launch `true` if the feature module should be launched, else `false`.
     */
    private fun onSuccessfulLoad(moduleName: String, launch: Boolean) {
        if (launch) {
            when (moduleName) {
                moduleNews -> launchActivity(newsArticleClassname)

            }
        }

        displayButtons()
    }

    /** Launch an activity by its class name. */
    private fun launchActivity(className: String) {
        Intent().setClassName("com.tools.jackofall", className)
                .also {
                    startActivity(it)
                }
    }

    fun toastAndLog(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
        Log.d(TAG, text)
    }

    private val TAG = "DynamicFeatures"
    override fun getFactory() = defaultViewModelProviderFactory

    override fun getViewModel() = MainViewModel::class.java

    override fun getFragmentLayout() = R.layout.fragment_main
    override fun getToolBar() = binding.appbar.toolbar

}