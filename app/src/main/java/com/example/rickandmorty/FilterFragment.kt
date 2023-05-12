package com.example.rickandmorty

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseDialog
import com.example.rickandmorty.databinding.FragmentFilterBinding
import com.example.rickandmorty.presentation.fragments.models.SelectedFilters
import com.example.rickandmorty.utils.setOnCheckedChangeListenerRetrieveText
import com.example.rickandmorty.utils.directionsSafeNavigation

class FilterFragment : BaseDialog<FragmentFilterBinding>() {

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentFilterBinding.inflate(inflater)

    private val filterSettings = SelectedFilters()

    override fun initialize() {
        super.initialize()
        dialog?.setCancelable(true)
        dialog?.setCanceledOnTouchOutside(true)
    }

    override fun setupListeners() = with(binding) {
        radioButtonAlien.setOnCheckedChangeListenerRetrieveText {
            filterSettings.status = it
        }
        radioButtonDead.setOnCheckedChangeListenerRetrieveText {
            filterSettings.status = it
        }
        radioButtonMale.setOnCheckedChangeListenerRetrieveText {
            filterSettings.gender = it
        }
        radioButtonFemale.setOnCheckedChangeListenerRetrieveText {
            filterSettings.gender = it
        }
        radioButtonHuman.setOnCheckedChangeListenerRetrieveText {
            filterSettings.species = it
        }
        radioButtonAlien.setOnCheckedChangeListenerRetrieveText {
            filterSettings.species = it
        }
        buttonEnter.setOnClickListener {
            findNavController().directionsSafeNavigation(
                FilterFragmentDirections.actionFilterFragmentToCharactersFragment()
            )
        }

    }

}