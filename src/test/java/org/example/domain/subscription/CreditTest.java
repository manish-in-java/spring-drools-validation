package org.example.domain.subscription;

import org.drools.command.CommandFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.example.domain.subscription.Credit;
import org.example.domain.subscription.Scheme;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:springContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CreditTest
{
  private static final Random RANDOM = new SecureRandom();

  @Autowired
  @Qualifier("creditKnowledgeSession")
  private StatelessKnowledgeSession creditRulesProcessor;

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
