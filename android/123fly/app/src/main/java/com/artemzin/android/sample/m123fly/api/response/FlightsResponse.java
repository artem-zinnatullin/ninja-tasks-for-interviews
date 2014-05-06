package com.artemzin.android.sample.m123fly.api.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * Here is sample JSON http://test.morbo.ru/test.json
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class FlightsResponse extends BaseResponse {

    private List<Data> mDataList;

    public List<Data> getDataList() {
        return mDataList;
    }

    public void setDataList(List<Data> dataList) {
        mDataList = dataList;
    }

    public static class Data {
        private String mHash;
        private String mAirline;
        private FlightTo mFlightTo;
        private List<Offer> mOffers;

        public String getHash() {
            return mHash;
        }

        public void setHash(String hash) {
            mHash = hash;
        }

        public String getAirline() {
            return mAirline;
        }

        public void setAirline(String airline) {
            mAirline = airline;
        }

        public FlightTo getFlightTo() {
            return mFlightTo;
        }

        public void setFlightTo(FlightTo flightTo) {
            mFlightTo = flightTo;
        }

        public List<Offer> getOffers() {
            return mOffers;
        }

        public void setOffers(List<Offer> offers) {
            mOffers = offers;
        }

        public static class FlightTo {
            private Long mDuration;
            private List<Segment> mSegments;

            // I don't know what is flightBack, so I am not using it

            public Long getDuration() {
                return mDuration;
            }

            public void setDuration(Long duration) {
                mDuration = duration;
            }

            public List<Segment> getSegments() {
                return mSegments;
            }

            public void setSegments(List<Segment> segments) {
                mSegments = segments;
            }

            public static class Segment {
                private String mAirline;
                private String mFlightNumber;
                private String mPlane;
                private String mDeparturePoint;
                private String mArrivalPoint;
                private String mServiceClass;
                private Long mDepartureTime;
                private Long mArrivalTime;
                private Long mDuration;
                private Long mLayoverTime;

                public String getAirline() {
                    return mAirline;
                }

                public void setAirline(String airline) {
                    mAirline = airline;
                }

                public String getFlightNumber() {
                    return mFlightNumber;
                }

                public void setFlightNumber(String flightNumber) {
                    mFlightNumber = flightNumber;
                }

                public String getPlane() {
                    return mPlane;
                }

                public void setPlane(String plane) {
                    mPlane = plane;
                }

                public String getDeparturePoint() {
                    return mDeparturePoint;
                }

                public void setDeparturePoint(String departurePoint) {
                    mDeparturePoint = departurePoint;
                }

                public String getArrivalPoint() {
                    return mArrivalPoint;
                }

                public void setArrivalPoint(String arrivalPoint) {
                    mArrivalPoint = arrivalPoint;
                }

                public String getServiceClass() {
                    return mServiceClass;
                }

                public void setServiceClass(String serviceClass) {
                    mServiceClass = serviceClass;
                }

                public Long getDepartureTime() {
                    return mDepartureTime;
                }

                public void setDepartureTime(Long departureTime) {
                    mDepartureTime = departureTime;
                }

                public Long getArrivalTime() {
                    return mArrivalTime;
                }

                public void setArrivalTime(Long arrivalTime) {
                    mArrivalTime = arrivalTime;
                }

                public Long getDuration() {
                    return mDuration;
                }

                public void setDuration(Long duration) {
                    mDuration = duration;
                }

                public Long getLayoverTime() {
                    return mLayoverTime;
                }

                public void setLayoverTime(Long layoverTime) {
                    mLayoverTime = layoverTime;
                }
            }
        }

        public static class Offer {
            private String mOrigin;
            private String mLink;
            private BigDecimal mPrice;

            public String getOrigin() {
                return mOrigin;
            }

            public void setOrigin(String origin) {
                mOrigin = origin;
            }

            public String getLink() {
                return mLink;
            }

            public void setLink(String link) {
                mLink = link;
            }

            public BigDecimal getPrice() {
                return mPrice;
            }

            public void setPrice(BigDecimal price) {
                mPrice = price;
            }
        }
    }

}
