package wad.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        fo.setContentType(file.getContentType());
        fo.setContentLength(file.getSize());
        fo.setContent(file.getBytes());

        foRepository.save(fo);

        return "redirect:/files";
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public String deleteFile(@PathVariable long id){
        if(foRepository.exists(id))foRepository.delete(id);
        return "redirect:/files";
    }
    
}