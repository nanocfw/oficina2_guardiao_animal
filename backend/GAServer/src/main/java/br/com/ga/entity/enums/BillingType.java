package br.com.ga.entity.enums;

public enum BillingType {
    PER_HOUR, PER_DAY;

    @Override
    public String toString() {
        return asString(this);
    }

    public static String asString(BillingType billingType) {
        switch (billingType) {
            case PER_HOUR:
                return "Por Hora";
            case PER_DAY:
                return "Por Dia";
            default:
                return "Inválido";
        }
    }

    public static String[] getAll() {
        BillingType[] billingTypes = values();
        String[] aux = new String[billingTypes.length];

        for (int i = 0; i < billingTypes.length; i++)
            aux[i] = asString(billingTypes[i]);

        return aux;
    }
}
