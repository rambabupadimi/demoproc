package com.cenrefordentistry.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.MyPracticeAdapter;
import com.cenrefordentistry.adapters.VoucherWalletAdapter;
import com.cenrefordentistry.daos.VoucherDAO;
import com.cenrefordentistry.models.VoucherModel;

import java.util.List;

/**
 * Created by Ramu on 14-07-2017.
 */

public class VoucherWallet extends Fragment {

    private RecyclerView voucherWalletRecyclerview;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voucher_wallet,container, false);

        VoucherDAO voucherDAO = new VoucherDAO(getContext());
        List<VoucherModel> voucherWalletList = voucherDAO.getVoucherData();

        voucherWalletRecyclerview   = (RecyclerView) view.findViewById(R.id.voucher_wallet_recyclerview);
        voucherWalletRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        VoucherWalletAdapter voucherWalletAdapter = new VoucherWalletAdapter(getActivity(),voucherWalletList);
        voucherWalletRecyclerview.setAdapter(voucherWalletAdapter);
        return view;
    }

}
