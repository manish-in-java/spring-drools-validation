package org.example.domain.subscription;

import java.math.BigDecimal;

public class Credit
{
  private final BigDecimal amount;
  private BigDecimal bonus = BigDecimal.ZERO;
  private final Scheme scheme;

  public Credit(final Scheme scheme, final BigDecimal amount)
  {
    this.amount = amount;
    this.scheme = scheme;
  }

  public BigDecimal getAmount()
  {
    return amount;
  }

  public BigDecimal getBonus()
  {
    return bonus;
  }

  public Scheme getScheme()
  {
    return scheme;
  }

  public void setBonus(BigDecimal bonus)
  {
    this.bonus = bonus;
  }
}
