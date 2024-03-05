import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SHSController {

    @Autowired
    private SHSService shsService;

    @GetMapping("/shs/displayInfo")
    public String displayInfo() {
        return shsService.getSHSInfo();
    }
}
