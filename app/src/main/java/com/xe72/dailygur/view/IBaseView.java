package com.xe72.dailygur.view;

import com.xe72.dailygur.dto.InnerData;

import java.util.List;

public interface IBaseView {

    MainActivity getMainActivity();

    void updateList(List<InnerData> imageList);

    void makeToast(String s);

    void closeRefreshing();
}
