package org.example.multithreading.OddEven;

public enum Turn {
    EVEN {
        @Override
        public Turn next() {
            return ODD;
        }
    },
    ODD {
        @Override
        public Turn next() {
            return EVEN;
        }
    };

    public abstract Turn next();
}

