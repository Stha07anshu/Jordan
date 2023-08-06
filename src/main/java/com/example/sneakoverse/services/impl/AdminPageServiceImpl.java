package com.example.sneakoverse.services.impl;

import com.example.sneakoverse.entity.AdminPage;
import com.example.sneakoverse.pojo.AdminPagePojo;
import com.example.sneakoverse.repo.AdminPageRepo;
import com.example.sneakoverse.services.AdminPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminPageServiceImpl implements AdminPageService {
    private final AdminPageRepo adminPageRepo;
    private final String UPLOAD_DIRECTORY=System.getProperty("user.dir")+"/file_server";

    @Override
    public void saveData(AdminPagePojo adminPagePojo) throws IOException {
        AdminPage adminPage = new AdminPage();
        adminPage.setId(adminPagePojo.getId());
        adminPage.setItem_name(adminPagePojo.getItem_name());
        adminPage.setItem_price(adminPagePojo.getItem_price());
        adminPage.setItem_rating(adminPagePojo.getItem_rating());

        if(adminPagePojo.getImage()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, adminPagePojo.getImage().getOriginalFilename());
            fileNames.append(adminPagePojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, adminPagePojo.getImage().getBytes());

            adminPage.setImage(adminPagePojo.getImage().getOriginalFilename());
        }


        adminPageRepo.save(adminPage);

    }

    @Override
    public List<AdminPage> getData() {
        return adminPageRepo.findAll();
    }
    @Override
    public void deleteById(Integer id) {
        adminPageRepo.deleteById(id);
    }

    @Override
    public Optional<AdminPage> getById(Integer id) {
        return adminPageRepo.findById(id);
    }

    @Override
    public void dropById(Integer id) { adminPageRepo.deleteById(id);
    }

    @Override
    public List<AdminPage> getAllData() {
        return adminPageRepo.findAll();
    }

    @Override
    public Optional<AdminPage> fetchById(Integer id) {
        return adminPageRepo.findById(id);
    }

    @Override
    public AdminPage getByIdNotOpt(Integer id) {
        return adminPageRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }
}
