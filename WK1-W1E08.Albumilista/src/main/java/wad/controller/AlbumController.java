package wad.controller;

import java.util.ArrayList;
import java.util.List;
import wad.repository.AlbumRepository;
import wad.domain.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Track;
import wad.repository.TrackRepository;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private TrackRepository trackRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        return "/WEB-INF/views/page.jsp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createAlbum(@ModelAttribute Album album) {
        String name = album.getName();
        if (name != null && !name.trim().isEmpty()) {
            albumRepository.save(album);
        }

        return "redirect:/albums";
    }
    
    @RequestMapping(value="/{albumId}/tracks", method=RequestMethod.POST)
    public String addTrack(@PathVariable long albumId, String name, @ModelAttribute Track track){
        if (name!=null && !name.trim().isEmpty()){
            Album album = albumRepository.findOne(albumId);
            List<Track>trackList = new ArrayList();
            track.setName(name);
            trackList.add(track);
            album.setTracks(trackList);
            trackRepository.save(track);
        }
        
        return "redirect:/albums";
    }
    
    @Transactional
    @RequestMapping(value="/{albumId}/tracks/{trackId}/delete", method=RequestMethod.POST)
    public String deleteTrack(@PathVariable long albumId, @PathVariable long trackId){
        Album album = albumRepository.findOne(albumId);
        Track track = trackRepository.findOne(trackId);
        List<Track> trackList = album.getTracks();
        trackList.remove(track);
        trackRepository.delete(trackId);
        
        return "redirect:/albums";
    }

}
