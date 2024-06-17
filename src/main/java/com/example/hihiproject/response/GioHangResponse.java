package com.example.hihiproject.response;

import com.example.hihiproject.entity.KhachHang;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GioHangResponse {
    @Min(1)
    private int id;

    @NotEmpty
    private String tenNguoiNhan;
    @NotNull
    private LocalDateTime ngayTao;
    @NotEmpty
    private String diaChi;
    @NotEmpty
    private String sdt;
    @NotNull
    private KhachHang khachHang;
}
