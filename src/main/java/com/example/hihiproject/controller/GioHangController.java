package com.example.hihiproject.controller;

import com.example.hihiproject.entity.GioHang;
import com.example.hihiproject.entity.KhachHang;
import com.example.hihiproject.repository.GioHangRepository;
import com.example.hihiproject.repository.KhachHangRepository;
import com.example.hihiproject.request.GioHangRequest;
import com.example.hihiproject.response.GioHangResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Target;
import java.util.List;

@Controller
@RequestMapping("/gioHang")
public class GioHangController {
    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    GioHangRepository gioHangRepository;


    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/list")
    public String showList(Model model, @RequestParam(name="page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 3);
        Page<GioHang> gioHangPage = gioHangRepository.findAll(pageable);

        Page<GioHangResponse> listG = gioHangPage.map(
                gioHang -> modelMapper.map(gioHang, GioHangResponse.class)
        );

        model.addAttribute("listG", listG);
        model.addAttribute("gioHangRequest", new GioHangRequest());
        return "index";
    }



    @GetMapping("/update")
    public String updateNha(Model model, @RequestParam("id") int id){
        Pageable pageable = PageRequest.of(1, 3);
        Page<GioHang> gioHangPage = gioHangRepository.findAll(pageable);

        Page<GioHangResponse> listG = gioHangPage.map(
                gioHang -> modelMapper.map(gioHang, GioHangResponse.class)
        );
        model.addAttribute("listG", listG);

        GioHang gioHang= gioHangRepository.findById(id).get();
        GioHangResponse gioHangResponse = GioHangResponse.builder()
                .id(gioHang.getId())
                .diaChi(gioHang.getDiaChi())
                .khachHang(gioHang.getKhachHang())
                .ngayTao(gioHang.getNgayTao())
                .sdt(gioHang.getSdt())
                .tenNguoiNhan(gioHang.getTenNguoiNhan())
                .build();
        model.addAttribute("gioHangRequest", gioHangResponse);
        return "index";
    }

    @PostMapping("/save")
    public String saveNhe(Model model, @Validated @ModelAttribute("gioHangRequest") GioHangResponse gioHangResponse, BindingResult result){
        if (result.hasErrors()){
            List<GioHangResponse> listG = gioHangRepository.findAll().stream().map(
                    gioHang ->
                            GioHangResponse.builder()
                                    .id(gioHang.getId())
                                    .diaChi(gioHang.getDiaChi())
                                    .khachHang(gioHang.getKhachHang())
                                    .ngayTao(gioHang.getNgayTao())
                                    .sdt(gioHang.getSdt())
                                    .tenNguoiNhan(gioHang.getTenNguoiNhan())
                                    .build()
            ).toList();
            model.addAttribute("listG", listG);
            return "index";
        }
        GioHang gioHang = GioHang.builder()
                .id(gioHangResponse.getId())
                .diaChi(gioHangResponse.getDiaChi())
                .khachHang(gioHangResponse.getKhachHang())
                .ngayTao(gioHangResponse.getNgayTao())
                .sdt(gioHangResponse.getSdt())
                .tenNguoiNhan(gioHangResponse.getTenNguoiNhan())
                .build();
        gioHangRepository.save(gioHang);
        return "redirect:/gioHang/list";

    }
    @ModelAttribute("listKH")
    public List<KhachHang> pushKH(){
        return khachHangRepository.findAll();
    }


}
