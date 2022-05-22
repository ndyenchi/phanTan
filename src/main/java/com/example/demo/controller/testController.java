package com.example.demo.controller;

import com.example.demo.DTO.A;
import com.example.demo.DTO.B;
import com.example.demo.DTO.C;
import com.example.demo.DTO.KhoSPDto;
import com.example.demo.service.KhoSPService;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test/")
public class testController {
    @Autowired
    KhoSPService khoSPService;@Autowired
    SanPhamService sanPhamService;
    @GetMapping("{masp}")
    private A get(@PathVariable int masp){
        List<String> listColor=khoSPService.selectMauTheoMaSP(masp);
        A a=new A();
        List<B> listb=new ArrayList<>();
        for(int i=0;i<listColor.size();i++){
            List<KhoSPDto> list =khoSPService.selectbyID_Color(masp,listColor.get(i).toString());
            for(KhoSPDto l:list){
                C c =new C(l.getSize(),l.getSoLuongTon());
                B b=new B(listColor.get(i).toString(), c);
                listb.add(b);
                System.out.println("size"+ b);
            }

        }
        a.setChiTietSP(listb);
        a.setSanpham(sanPhamService.getbyID(masp));
        return a;
    }
}
