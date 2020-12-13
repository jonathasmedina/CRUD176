package com.example.crud176;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarFragment extends Fragment {

    ListView listView;
    String info;
    SearchView editsearch;
    ArrayList<String> arrayListUsuarioString = new ArrayList<>();
    ArrayList<String> arrayListUsuarioStringTemp = new ArrayList<>();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarFragment newInstance(String param1, String param2) {
        ListarFragment fragment = new ListarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar, container, false);

        listView = view.findViewById(R.id.listView1);
        editsearch = (SearchView) view.findViewById(R.id.searchView);

        List<Usuario> usuarioList = MainActivity.myAppDatabase.myDao().listarUsuarios();

        for(Usuario usuario: usuarioList){
            info = "";
            int id = usuario.getId();
            String nome = usuario.getNome();
            String email = usuario.getEmail();

            info = "Id: " + id + "\n" + "Nome: " + nome
                    + "\n" + "e-mail: " + email;

            arrayListUsuarioString.add(info);
        }

        //populando cópia do array para manipular a busca na lista
        arrayListUsuarioStringTemp.addAll(arrayListUsuarioString);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity().getBaseContext(),
                android.R.layout.simple_list_item_1,
                arrayListUsuarioString );

        listView.setAdapter(arrayAdapter);



        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //zerando a lista original
                arrayListUsuarioString.clear();

                //TODO considerar maiúsculas: https://abhiandroid.com/ui/searchview
                if (newText.length() == 0) {
                    arrayListUsuarioString.addAll(arrayListUsuarioStringTemp);
                }
                else {
                    //pesquisou por algo
                    //percorrendo a lista original no array temp e procurando o termo
                    for(String info: arrayListUsuarioStringTemp){
                        if(info.contains(newText)){
                            //atualizando a nova lista
                            arrayListUsuarioString.add(info);
                        }
                    }
                }

                arrayAdapter.notifyDataSetChanged();

                return false;
            }

        });


        return view;
    }
}