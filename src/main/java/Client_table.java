/**
 * Created by Alexander on 05.11.2016.
 */
public class Client_table {

        private int key_client;
        private String name_client;
        private String phone_client;
        private String note_client;
        private String adres;

        public Client_table(int key_client, String name_client, String phone_client,String adres,String note_client)
        {
            this.key_client = key_client;
            this.name_client = name_client;
            this.phone_client = phone_client;
            this.adres=adres;
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
        public String getPhone_client()
        {
            return phone_client;
        }
        public void setPhone_client(String phone_client)
        {
            this.phone_client = phone_client;
        }
        public String getAdres()
        {
            return adres;
        }
        public void setAdres(String adres)
        {
            this.adres=adres;
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
