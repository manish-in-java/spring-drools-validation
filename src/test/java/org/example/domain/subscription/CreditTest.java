package org.example.domain.subscription;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:springTestContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CreditTest
{
  private static final Random RANDOM = new SecureRandom();

  @KSession("creditKnowledgeSession")
  private StatelessKieSession creditRulesProcessor;

  @Test
  public void loadB1G1Scheme()
  {
    final Credit credit = new Credit(Scheme.B1G1, new BigDecimal(getDouble()));
    creditRulesProcessor.execute(CommandFactory.newInsert(credit));

    assertEquals(credit.getAmount(), credit.getBonus());
  }

  @Test
  public void loadB2G1Scheme()
  {
    final Credit credit = new Credit(Scheme.B2G1, new BigDecimal(getDouble()));
    creditRulesProcessor.execute(CommandFactory.newInsert(credit));

    assertEquals(credit.getAmount().multiply(new BigDecimal(0.5)), credit.getBonus());
  }

  @Test
  public void loadB3G1Scheme()
  {
    final Credit credit = new Credit(Scheme.B3G1, new BigDecimal(getDouble()));
    creditRulesProcessor.execute(CommandFactory.newInsert(credit));

    assertEquals(credit.getAmount().multiply(new BigDecimal(0.333333333)), credit.getBonus());
  }

  @Test
  public void loadB3G2Scheme()
  {
    final Credit credit = new Credit(Scheme.B3G2, new BigDecimal(getDouble()));
    creditRulesProcessor.execute(CommandFactory.newInsert(credit));

    assertEquals(credit.getAmount().multiply(new BigDecimal(0.666666666)), credit.getBonus());
  }

  private double getDouble()
  {
    return RANDOM.nextDouble();
  }
}
