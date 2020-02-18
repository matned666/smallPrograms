package personelInfo.mechanics;

public class Person {

        private String NAME;
        private String SURNAME;
        private int AGE;

        private String position;
        private int ID;


        public String getNAME() { return NAME; }
        public String getSURNAME() { return SURNAME; }
        public int getAGE() { return AGE; }

        private Person(PersonBuilder builder) {
            this.NAME = builder.NAME;
            this.SURNAME = builder.SURNAME;
            this.AGE = builder.AGE;
        }

        @Override
        public String toString() {
            return ID+";"+NAME+";"+SURNAME+";"+AGE+";\n";
        }

        static class PersonBuilder{

            private String NAME;
            private String SURNAME;
            private int AGE;

            private String position;
            private int ID;

            PersonBuilder(String NAME, String SURNAME, int AGE) {
                this.NAME = NAME;
                this.SURNAME = SURNAME;
                this.AGE = AGE;
            }

            PersonBuilder position(String position){
                this.position = position;
                return this;
            }
            PersonBuilder ID(int ID){
                this.ID = ID;
                return this;
            }

            public Person build(){ return new Person(this);}
            String getNAME() {
                return NAME;
            }
            String getSURNAME() {
                return SURNAME;
            }
            int getAGE() {
                return AGE;
            }
            int getID() {
                return ID;
            }
            String getPosition() {
                return position;
            }

            void setNAME(String NAME) {
                this.NAME = NAME;
            }
            void setSURNAME(String SURNAME) {
                this.SURNAME = SURNAME;
            }
            void setAGE(int AGE) {
                this.AGE = AGE;
            }
            void setPosition(String position) {
                this.position = position;
            }
        }
    }