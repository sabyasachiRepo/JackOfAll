package com.tools.core

import androidx.fragment.app.Fragment

abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    abstract val viewModel: T
}