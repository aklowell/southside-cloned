package org.launchcode.mybookgroup.models;

public enum BookGenre {

        FICTION ("Fiction"),
        NONFICTION ("Non-fiction"),
        HISTFICTION("Historical Fiction"),
        SCIFI ("Science Fiction"),
        BIO ("Biography"),
        AUTOBIO ("Autobiography"),
        SELFHELP ("Self Help");

        private final String name;

        BookGenre(String name) {
            this.name=name;
        }
        public String getName() {
            return name;
        }

    }

