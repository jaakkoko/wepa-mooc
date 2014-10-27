package wad.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.FileObject;
import wad.repository.FileObjectRepository;

@Controller
@RequestMapping("/files")
public class FileController{

    @Autowired
    FileObjectRepository foRepository;
    
    @RequestMapping(method=RequestMethod.GET)
    public String getFiles(Model model){
        model.addAttribute("files", foRepository.findAll());
        return "files";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        FileObject fo = new FileObject();

        fo.setName(file.getOriginalFilename());
        fo.setMediaType(file.getContentType());
        fo.setSize(file.getSize());
        fo.setContent(file.getBytes());

        foRepository.save(fo);

        return "redirect:/files";
    }
}