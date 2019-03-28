package com.rflpazini.badabin.router;

import com.rflpazini.badabin.handler.ProductHandler;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = {ProductHandler.class, ProductRouter.class})
public class ProductHandlerTest {


}
