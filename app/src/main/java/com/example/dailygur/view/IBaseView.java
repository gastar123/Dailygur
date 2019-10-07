package com.example.dailygur.view;

import com.example.dailygur.dto.InnerData;

import java.util.List;

public interface IBaseView {

    MainActivity getMainActivity();

    void updateList(List<InnerData> imageList);

    void makeToast(String s);

    void closeRefreshing();
}
