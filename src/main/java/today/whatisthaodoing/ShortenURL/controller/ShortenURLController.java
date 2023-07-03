package today.whatisthaodoing.ShortenURL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import today.whatisthaodoing.ShortenURL.model.ShortenURL;
import today.whatisthaodoing.ShortenURL.repository.Database;

@RestController
public class ShortenURLController {

    @Autowired
    private Database database;

    @RequestMapping(
            path = "/",
            method = RequestMethod.POST,
//            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    private ResponseEntity<String> shortenURL(@RequestParam("url") String originalURL) {
        //String msg = "IT'S WORKING " + originalURL;
        // TODO store if not pressent into the db
        String key = originalURL.substring(originalURL.length()-4, originalURL.length()-1);
        return ResponseEntity.ok().body("http://localhost:8080/"+key);
    }

    @RequestMapping(
            path = "/{key}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    private ResponseEntity<String> redirectOriginalURL(@PathVariable("key") String key) {
        // search original url based on the key
        // TODO
        // - get the original from the db with the key
        //      - if no key found -> 404
        //      - else implement the redirect without returning anything

        if (!database.existsById(key)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("http://original-url.nofake/kzgd/fxg/dzgsd");
    }

}
