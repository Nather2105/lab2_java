    package org.example;

    /**
     * The Airplane class represents specific information about an aircraft.
     * It extends the Transport class and includes additional attributes
     * such as the number of engines and whether it is a jet.
     */
    public class Airplane extends Transport {
        private final int numberOfEngines;
        private final boolean isJet;

        /**
         * Constructs a new Airplane object using the provided builder.
         *
         * @param builder The builder used to construct the Airplane object.
         */
        private Airplane(Builder builder) {
            super(builder);
            this.numberOfEngines = builder.numberOfEngines;
            this.isJet = builder.isJet;
        }

        /**
         * Get the number of engines of the airplane.
         *
         * @return The number of engines of the airplane.
         */
        public int getNumberOfEngines() {
            return numberOfEngines;
        }

        /**
         * Check if the airplane is a jet.
         *
         * @return True if the airplane is a jet, false otherwise.
         */
        public boolean isJet() {
            return isJet;
        }

        /**
         * The Builder class for constructing an Airplane object with parameters.
         */
        public static class Builder extends Transport.Builder {
            private int numberOfEngines;
            private boolean isJet;

            /**
             * Constructs a new Builder for creating an Airplane object.
             *
             * @param brand The brand of the airplane.
             * @param model The model of the airplane.
             * @param type  The type of the airplane.
             */
            public Builder(String brand, String model, double type) {
                super(brand, model, type);
            }

            /**
             * Set the number of engines for the airplane.
             *
             * @param numberOfEngines The number of engines to set.
             * @return The Builder object for method chaining.
             */
            public Builder setNumberOfEngines(int numberOfEngines) {
                this.numberOfEngines = numberOfEngines;
                return this;
            }

            /**
             * Set whether the airplane is a jet or not.
             *
             * @param isJet True if the airplane is a jet, false otherwise.
             * @return The Builder object for method chaining.
             */
            public Builder setJet(boolean isJet) {
                this.isJet = isJet;
                return this;
            }

            /**
             * Build an Airplane object using the information provided in the Builder.
             *
             * @return A new Airplane object.
             */
            @Override
            public Airplane build() {
                return new Airplane(this);
            }
        }
    }
