package com.demo.myapp.services.impl;
import com.demo.myapp.payloads.EmailDto;
import com.demo.myapp.payloads.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void ticketConfirmMailSender(EmailDto emailTemplate) throws MessagingException, MessagingException {
        String from = "priyeshkarn100@gmail.com";
        String to = "priyeshkarn200@gmail.com";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject("View your Booking Details");
        helper.setFrom(from);
        helper.setTo(to);

        BookingDto b = emailTemplate.getBooking();
      //  List<OccupancyDto> passengers = emailTemplate.getPassengerDetaials();

        String mailContent = "<b>Hello</b>,<br/><i>Thank you for choosing <b>BookMyBus</b> for bus reservation. Here is your booking deatils</i><br/>";
        mailContent = mailContent + "<table>\n" +
                "  <tr>\n" +
                "    <th>Booking Status</th>  \n" +
                "    <td>"+"Confirmed"+"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <th>Boarding From</th>\n" +
                "    <td>"+ b.getTrip_source() +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <th> Boarding To</th>  \n" +
                "    <td>"+b.getTrip_destination()+"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <th>Booking Date</th>  \n" +
                "    <td>"+b.getBooking_date()+"</td>\n" +
                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <th>Passenger name</th>  \n" +
//                "    <td>"+b.getPassenger_name()+"</td>\n" +
//                "  </tr>\n" +
                "  <tr>\n" +
                "  <th>Bus Number</th>  \n" +
                "    <td>"+b.getBus_id()+"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "  <th>No Of Tickets</th>\n" +
                "    <td>"+ b.getCount() +"</td>\n" +
                "  </tr>\n" +
                " <th>Total amount</th>\n" +
                "    <td>"+ b.getTotal_fare() +"</td>\n" +
                "  </tr>\n" +
                "  <th>PNR NUMBER</th>\n" +
                "    <td>"+ b.getBooking_id() +"</td>\n" +
                "  </tr>\n" +
                "</table>";
        mailContent = mailContent + "<p>BookMyBus wishes you a Happy and safe Journey.</p><br/> <b>Thank You</b>";

//        mailContent = mailContent + "<table>\n" +
//                "  <tr>\n" +
//                "    <th>Name</th>\n" +
//                "    <th>Age</th>\n" +
//                "    <th>Gender</th>\n" +
//                "  </tr>" ;
//
//        for( OccupancyDto passenger: passengers ){
//            mailContent = mailContent + "<tr>\n" +
//                    "    <td>" + passenger.getPassenger_name() + "</td>\n" +
//                    "    <td>" + passenger.getPassenger_age() + "</td>\n" +
//                    "    <td>" + passenger.getPassenger_gender() + "</td>\n" +
//                    "  </tr>";
//        }

        mailContent = mailContent + "</table>\n";
        boolean html = true;
        helper.setText(mailContent, html);
        mailSender.send(message);
        System.out.println("------------------Email sent ---------------");
    }
}