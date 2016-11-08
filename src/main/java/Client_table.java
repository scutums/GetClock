/**
 * Created by Alexander on 05.11.2016.
 */
public class Client_table {

        private int key_client;
        private String name_client;
        private int phone_client;
        private String note_client;

        public Client_table(int key_client, String name_client, int phone_client,String note_client)
        {
            this.key_client = key_client;
            this.name_client = name_client;
            this.phone_client = phone_client;
            this.note_client = note_client;

        }
        public Client_table(){
        }

        public int getKey_client()
        {
            return key_client;
        }
        public void setKey_client(int key_client)
        {
            this.key_client = key_client;
        }
        public String getName_client()
        {
            return name_client;
        }
        public void setName_client(String name_client)
        {
            this.name_client = name_client;
        }
        public int getPhone_client()
        {
            return phone_client;
        }
        public void setPhone_client(int phone_client)
        {
            this.phone_client = phone_client;
        }
        public String getNote_client()
        {
            return note_client;
        }
        public void setNote_client(String note_client)
        {
            this.note_client=note_client;
        }



}
