package com.example;

public enum CoinTypes {
    _5pln(5d),
    _2pln(2d),
    _1pln(1d),
    _50gr(0.5d),
    _20gr(0.2d),
    _10gr(0.1d),
    _5gr(0.05d),
    _2gr(0.02d),
    _1gr(0.01d);

    private final double value;
    private CoinTypes(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    
    public static CoinTypes findBiggestCoinToSubtract(double value,int max)
    {
        for (int i = max; i < CoinTypes.values().length; i++) {
            if (value>=CoinTypes.values()[i].getValue()*100) {
                return CoinTypes.values()[i];
            }
        }
        return null;
    }

    
}
