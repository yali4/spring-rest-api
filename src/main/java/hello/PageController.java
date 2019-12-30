package hello;

import hello.MySQL.Entity.Page;
import hello.MySQL.Repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PageController {

    @Autowired
    private PageRepository pageRepository;

    @RequestMapping("/page")
    public Iterable<Page> pageList(){
        return this.pageRepository.findAll();
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public void createPage(@RequestParam("title") String title, @RequestParam("body") String body){
        Page page = new Page();
        page.setTitle(title);
        page.setBody(body);
        this.pageRepository.save(page);
    }

    @RequestMapping(value = "/page/{id}", method = RequestMethod.DELETE)
    public void deletePage(@PathVariable("id") Integer id) {
        this.pageRepository.deleteById(id);
    }
}