/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uitest;

import java.io.File;
import javax.swing.DefaultListModel;

/**
 *
 * @author ADMIN
 */
public class preLoad {
    public DefaultListModel preLoadData(){
        String inputFolder = "src/main/java/com/mycompany/uitest/input_images";
                File myfolder = new File(inputFolder);
                File[] MylistOfImages = myfolder.listFiles();
                
                DefaultListModel MyJlistModel = new DefaultListModel();
                for (File MylistOfImage : MylistOfImages) {
                    if (MylistOfImage.isFile()) {
                        String myfiles = MylistOfImage.getName();
                        MyJlistModel.addElement(myfiles);
                    }
                }
           return MyJlistModel;     
                
    }
}
