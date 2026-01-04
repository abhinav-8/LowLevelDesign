package org.example.multithreading.OddEvenZeroFibbonacci;

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
            return ZERO;
        }
    },
    ZERO {
        @Override
        public Turn next() {
            return FIBONACCI;
        }
    },
    FIBONACCI {
        @Override
        public Turn next() {
            return EVEN;
        }
    };

    public abstract Turn next();
}

