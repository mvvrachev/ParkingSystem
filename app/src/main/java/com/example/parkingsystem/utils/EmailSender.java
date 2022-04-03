package com.example.parkingsystem.utils;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender extends AsyncTask{

    private Session session;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected Void doInBackground(Object[] objects) {

        StringBuilder reservations = new StringBuilder();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("reservations").whereEqualTo("date", DatesHelper.INSTANCE.getTodayDate())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                reservations.append(document.getData()).append("\n");
                            }
                        } else {
                            Log.d("tag", "Error getting documents: ", task.getException());
                        }
                    }
                });

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("parkingsystem4@gmail.com", "parkingsystem123");
            }
        });
        try {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress("parkingsystem4@gmail.com"));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress("parkingsystem4@gmail.com"));
            mm.setSubject("Reservations information");
            mm.setText(String.valueOf(reservations));
            Transport.send(mm);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}