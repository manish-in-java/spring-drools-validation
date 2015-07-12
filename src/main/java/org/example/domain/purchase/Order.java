package org.example.domain.purchase;

import org.example.domain.profile.Party;

public class Order
{
  private final Party     buyer;
  private final Party     seller;
  private final OrderType type;

  public Order(final Party seller, final Party buyer, final OrderType type)
  {
    this.buyer = buyer;
    this.seller = seller;
    this.type = type;
  }

  public Party getBuyer()
  {
    return buyer;
  }

  public Party getSeller()
  {
    return seller;
  }

  public OrderType getType()
  {
    return type;
  }
}
