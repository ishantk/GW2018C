package com.auribises.gw2018c;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LowerFragment extends Fragment implements AdapterView.OnItemClickListener{


    ListView listView;
    ArrayAdapter<String> adapter;

    MyItemClickListener clickListener;

    public void setMyItemClickListener(MyItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public LowerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lower, container, false);

        listView = view.findViewById(R.id.listView);

        //adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1);
        adapter.add("CNN");
        adapter.add("NDTC");
        adapter.add("BBC");
        adapter.add("ZEE");
        adapter.add("AAJ TAK");

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        clickListener.itemClicked(position);
    }
}
