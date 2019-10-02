package com.example.spidertest.view;

import com.example.spidertest.dto.InnerData;

import java.util.List;

public interface IBaseView {

    void updateList(List<InnerData> imageList);

    void makeToast(String s);
}
