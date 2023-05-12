package com.example.rickandmorty

import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import com.example.core.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.presentation.fragments.adapter.CharacterPagAdapter
import com.example.rickandmorty.presentation.fragments.viewModel.CharacterViewModel
import com.example.rickandmorty.utils.LoaderStateAdapter
import com.example.rickandmorty.utils.loadListener
import com.example.rickandmorty.utils.safeFlowGather
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterFragment : BaseFragment<FragmentCharacterBinding, CharacterViewModel>() {

    override val viewModel: CharacterViewModel by viewModel()
    private val characterAdapter = CharacterPagAdapter()
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentCharacterBinding.inflate(inflater)

    override fun initView() {
        super.initView()
        initAdapter()
        getCharacters()
        searchView()
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getCharacters()
    }
    private fun initAdapter() = with(binding) {
        recyclerCharacters.adapter = characterAdapter.withLoadStateFooter(
            footer = LoaderStateAdapter()
        )
        loadListener(progressCharactersLoader, recyclerCharacters)
    }
    private fun searchView() = with(binding) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (searchView.query.trim().isNotEmpty() && query!!.length > 2) {
                    getSearchName(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (searchView.query.trim().isNotEmpty() && newText!!.length > 2) {
                    getSearchName(newText)
                }
                if (searchView.query.trim().isEmpty()) {
                    viewModel.getCharacters(name = " ", status = " ", species = " ", gender = " ")

                }
                getCharacters()
                return true
            }
        })
    }
    private fun getSearchName(search: String?) {
        viewModel.getCharacters(search)
        getCharacters()
    }
    private fun getCharacters() {
        safeFlowGather {
            viewModel.characters.collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

}