package com.example.x550vx_dm066t.myapplication.property;

/**
 * Created by x550vx-dm066t on 20/10/2017.
 */

public class comments {
        private String id;
        private String by;
        private String kids;
        private String text;
        private String time;
        private String type;

    public comments(){}

       public comments(String _id, String _by,String _kids,String _text,String _time,String _type){
            id = _id;
            by = _by;
            kids = _kids;
           text = _text;
            time = _time;
            type = _type;
       }

        public void setId(String id) {
            this.id = id;
        }

        public void setBy(String by) {
            this.by = by;
        }

        public void setKids(String kids) {
            this.kids = kids;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setType(String type) {
            this.type = type;
        }



        public String getId() {
            return id;
        }

        public String getBy() {
            return by;
        }

        public String getKids() {
            return kids;
        }

        public String getText() {
            return text;
        }

        public String getTime() {
            return time;
        }

        public String getType() {
            return type;
        }


}
