package hello;

import hello.Service.ExchangeRateService;
import hello.Service.Model.ExchangeRate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RateController {

    @RequestMapping("/rate")
    public List<ExchangeRate> getRates() {
        ExchangeRateService service = new ExchangeRateService();
        return service.getRates();
    }

}
