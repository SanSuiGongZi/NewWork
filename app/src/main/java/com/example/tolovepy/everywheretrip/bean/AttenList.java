package com.example.tolovepy.everywheretrip.bean;

import java.util.List;

public class AttenList {
    /**
     * code : 0
     * desc :
     * result : {"page":1,"limit":20,"count":1,"banmi":[{"id":57,"name":"吴晓波","location":"杭州","occupation":"著名财经作家 青年领袖","introduction":"大家好，我是吴晓波。作为一个写字的人，深知\"读万卷书\"的重要，不过读书读到我这个年龄，有时候会生出无书可读的感叹，这时我常会选择\"行万里路\"，在旅程中寻找书本中无法获得的感受。\n\n无论目的和方式如何多变，旅行最终将落脚在体验二字，这是我和伴米旅行的共识，也是我们力求达到的效果。就算和千万人去了同一个地方，你也能通过我们的分享获得独有的体验，而这些体验会积累成为收获和知识，让旅行从一种短暂的休闲方式变成一种持久且让人成长的爱好。","following":5624,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1520393823697_c68268595efc905fc397781b6cde03c7.jpg","isFollowed":true}]}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * page : 1
         * limit : 20
         * count : 1
         * banmi : [{"id":57,"name":"吴晓波","location":"杭州","occupation":"著名财经作家 青年领袖","introduction":"大家好，我是吴晓波。作为一个写字的人，深知\"读万卷书\"的重要，不过读书读到我这个年龄，有时候会生出无书可读的感叹，这时我常会选择\"行万里路\"，在旅程中寻找书本中无法获得的感受。\n\n无论目的和方式如何多变，旅行最终将落脚在体验二字，这是我和伴米旅行的共识，也是我们力求达到的效果。就算和千万人去了同一个地方，你也能通过我们的分享获得独有的体验，而这些体验会积累成为收获和知识，让旅行从一种短暂的休闲方式变成一种持久且让人成长的爱好。","following":5624,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1520393823697_c68268595efc905fc397781b6cde03c7.jpg","isFollowed":true}]
         */

        private int page;
        private int limit;
        private int count;
        private List<BanmiBean> banmi;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<BanmiBean> getBanmi() {
            return banmi;
        }

        public void setBanmi(List<BanmiBean> banmi) {
            this.banmi = banmi;
        }

        public static class BanmiBean {
            /**
             * id : 57
             * name : 吴晓波
             * location : 杭州
             * occupation : 著名财经作家 青年领袖
             * introduction : 大家好，我是吴晓波。作为一个写字的人，深知"读万卷书"的重要，不过读书读到我这个年龄，有时候会生出无书可读的感叹，这时我常会选择"行万里路"，在旅程中寻找书本中无法获得的感受。

             无论目的和方式如何多变，旅行最终将落脚在体验二字，这是我和伴米旅行的共识，也是我们力求达到的效果。就算和千万人去了同一个地方，你也能通过我们的分享获得独有的体验，而这些体验会积累成为收获和知识，让旅行从一种短暂的休闲方式变成一种持久且让人成长的爱好。
             * following : 5624
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1520393823697_c68268595efc905fc397781b6cde03c7.jpg
             * isFollowed : true
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private int following;
            private String photo;
            private boolean isFollowed;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }
        }
    }
}
