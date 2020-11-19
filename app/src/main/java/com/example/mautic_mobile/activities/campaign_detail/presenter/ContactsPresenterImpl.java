package com.example.mautic_mobile.activities.campaign_detail.presenter;
import com.example.mautic_mobile.api.entities.Contact;
import com.example.mautic_mobile.api.entities.ContactsResponse;
import com.example.mautic_mobile.api.entities.ContactsOfCampaign;
import com.example.mautic_mobile.api.entities.ContactsOfCampaignResponse;
import com.example.mautic_mobile.api.services.MauticClient;
import java.util.ArrayList;
import java.util.List;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
public class ContactsPresenterImpl implements ContactsContract.ContactPresenter {
    private ContactsContract.ContactView contactView;
    private MauticClient mauticClient;

    public ContactsPresenterImpl(ContactsContract.ContactView contactView, MauticClient mauticClient) {
        this.contactView = contactView;
        this.mauticClient = mauticClient;
    }

    @Override
    public void onActivityLoaded(int campaignId) {
        contactView.showProgress();
        mauticClient.getContactsOfCampaign(campaignId)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ContactsOfCampaignResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ContactsOfCampaignResponse contactsOfCampaignResponse) {
                        List<ContactsOfCampaign> contactsOfCampaigns = contactsOfCampaignResponse.getContacts();
                        List<Integer> contactsId = new ArrayList<>();
                        for (int i = 0; i < contactsOfCampaigns.size(); i++) {
                            contactsId.add(contactsOfCampaigns.get(i).getLead_id());
                        }
                        mauticClient.getAllContacts() //Api don't support filter by list of ID (so must compare with all contacts)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<ContactsResponse>() {
                                    private List<Contact> result = new ArrayList<>();

                                    @Override
                                    public void onCompleted() {
                                        contactView.hideProgress();
                                        if (result.isEmpty()) {
                                            contactView.hideContentLayout();
                                            contactView.showMessageLayout();
                                        } else {
                                            contactView.showContentLayout();
                                            contactView.hideMessageLayout();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        System.out.println(e.getMessage());
                                    }

                                    @Override
                                    public void onNext(ContactsResponse contactResponse) {
                                        List<Contact> allContacts = new ArrayList<>(contactResponse.getContacts().values());

                                        for (int i = 0; i < allContacts.size(); i++) {
                                            if (contactsId.contains(allContacts.get(i).getId())) {
                                                result.add(allContacts.get(i));
                                            }
                                        }
                                        contactView.setDataToAdapter(result);
                                    }
                                });
                    }
                });
    }

}
