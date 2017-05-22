package org.example.domain.purchase;

import org.example.domain.profile.Party;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:springContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderTest
{
  @KSession("creditKnowledgeSession")
  private StatelessKieSession orderRulesProcessor;

  private final Party buyer  = new Party();
  private final Party seller = new Party();

  @Test
  public void testConsignmentOrder()
  {
    testOrder(new Order(seller, buyer, OrderType.CONSIGNMENT), 0);

    testOrder(new Order(buyer, seller, OrderType.CONSIGNMENT), 0);

    testOrder(new Order(buyer, buyer, OrderType.CONSIGNMENT), 1);

    testOrder(new Order(seller, seller, OrderType.CONSIGNMENT), 1);
  }

  @Test
  public void testSalesOrder()
  {
    testOrder(new Order(seller, buyer, OrderType.SALE), 0);

    testOrder(new Order(buyer, seller, OrderType.SALE), 0);

    testOrder(new Order(buyer, buyer, OrderType.SALE), 1);

    testOrder(new Order(seller, seller, OrderType.SALE), 1);
  }

  @Test
  public void testTransferOrder()
  {
    testOrder(new Order(buyer, buyer, OrderType.TRANSFER), 0);

    testOrder(new Order(seller, seller, OrderType.TRANSFER), 0);

    testOrder(new Order(buyer, seller, OrderType.TRANSFER), 1);

    testOrder(new Order(seller, buyer, OrderType.TRANSFER), 1);
  }

  private void testOrder(final Order order, final int errorCount)
  {
    final Set<String> errors = new HashSet<>();

    orderRulesProcessor.execute(Arrays.asList(order, errors));

    assertEquals(errorCount, errors.size());
  }
}
