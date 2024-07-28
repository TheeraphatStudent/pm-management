package components;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import pages.Member;
import pages.Page;
import resource.colors.MainColor;
import resource.environment.WindowClosingFrameEvent;
import utils.useButton;

public class Entrypagepanel extends JPanel{
    public Entrypagepanel(JFrame thispage,String text,String destpage,int top,int left,int bottom,int right){
        GridBagConstraints gridConst = new GridBagConstraints();
        // setBackground(MainColor.primary());
        setPreferredSize(new Dimension(100,500));
        setLayout(new GridBagLayout());

        JButton navigateToPage = new JButton(text);
        navigateToPage.addActionListener(e -> {
            if (destpage.equals("page")) {
                JFrame page = new Page();
            new WindowClosingFrameEvent(thispage, page);
            page.setVisible(true);
            thispage.dispose();
            }
            else if (destpage.equals("member")){
                JFrame member = new Member();
                new WindowClosingFrameEvent(thispage, member);
                member.setVisible(true);
                thispage.dispose();
            }
            else{
                System.out.println("Page not found");
            }

        });




       
    }
}
