package pl.looksoft.lsportfolio.network;

import java.util.List;

public class DetailResponse {
    int status;
    Data data;

    public int getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "DetailResponse{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    public static class Data {
        String name;
        String description;
        List<String> gallery;
        List<Link> link;

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public List<String> getGallery() {
            return gallery;
        }

        public List<Link> getLink() {
            return link;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", gallery=" + gallery +
                    ", link=" + link +
                    '}';
        }
    }

    public static class Link {
        String url;
        String image;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "url='" + url + '\'' +
                    ", image='" + image + '\'' +
                    '}';
        }
    }
}
