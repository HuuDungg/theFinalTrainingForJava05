package com.example.hihiproject.entity;

import jakarta.persistence.*;
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
@Entity
@Builder
@Table(name = "gio_hang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String tenNguoiNhan;
    @NotNull
    private LocalDateTime ngayTao;
    @NotEmpty
    private String diaChi;
    @NotEmpty
    private String sdt;

    @JoinColumn(name = "id_khach_hang", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private KhachHang khachHang;

}
