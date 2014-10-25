package wad.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.FileObject;
import wad.repository.FileObjectRepository;

@Controller
@RequestMapping("/gifs")
public class GifController {

    @Autowired
    FileObjectRepository foRepo;
    
    @RequestMapping(method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/gifs/1";
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public String get(Model model,@PathVariable("id") long id) {
        model.addAttribute("count", foRepo.findAll().size());
        model.addAttribute("current", id);
        model.addAttribute("next",foRepo.exists(id+1) ? id+1 : null);
        model.addAttribute("previous", foRepo.exists(id-1) ? id-1 : null);
        return "gifs";
    }
    
    @RequestMapping(value="/{id}/content",method = RequestMethod.GET, 
            produces="image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return foRepo.findOne(id).getContent();
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String addFile(@RequestParam MultipartFile file) throws IOException {
        FileObject fo = new FileObject();
        if (file.getContentType().equals("image/gif")){
            fo.setContent(file.getBytes());
            foRepo.save(fo);        
        }
        return "redirect:/gifs";
    }
}
