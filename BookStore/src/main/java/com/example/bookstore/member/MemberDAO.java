package com.example.bookstore.member;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {

    //member Variables
    static DataSource ds;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;


    //Constructor

    public MemberDAO() {
        //Connection Pool.
        try {
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Methods
    public boolean checkMember(String name) {

        boolean ret = false;
        String sql = "select * from membership where name = '"+name+"';";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ret = rs.next();

            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ret;
    }

    public int newMembership(MemberDTO info) {
        String sql = "insert into membership (name, passwd, email, phone, address, joindate)";

        int res = 0;

        sql += "value ('"+info.getName()+"', '"+info.getPasswd()+ "'";

        if(!info.getEmail().trim().equals("")) {
            sql += ", '"+info.getEmail()+"'";
        }

        if(!info.getPhone().trim().equals("")) {
            sql += ", '"+info.getPhone()+"'";
        }

        if(!info.getAddress().trim().equals("")) {
            sql += ", '"+info.getAddress()+"'";
        }

        sql += ", curTime());";

        System.out.println(sql);

        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            res = ps.executeUpdate();

            con.close();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList <MemberDTO> newMemberList(){
        ArrayList <MemberDTO> list = new ArrayList <MemberDTO> ();
        String sql = "select * from membership;";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setNo(rs.getInt("no"));
                member.setName(rs.getString("name"));
                member.setId(rs.getString("id"));
                member.setPasswd(rs.getString("passwd"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoindate(rs.getString("joindate"));
                list.add(member);
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList <MemberDTO> newMemberList(int no){
        ArrayList <MemberDTO> list = new ArrayList <MemberDTO> ();
        String sql = "select * from membership where no='"+no+"';";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setNo(rs.getInt("no"));
                member.setName(rs.getString("name"));
                member.setId(rs.getString("id"));
                member.setPasswd(rs.getString("passwd"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoindate(rs.getString("joindate"));
                list.add(member);
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList <MemberDTO> newMemberList(String id){
        ArrayList <MemberDTO> list = new ArrayList <MemberDTO> ();
        String sql = "select * from membership where id='"+id+"';";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setNo(rs.getInt("no"));
                member.setName(rs.getString("name"));
                member.setId(rs.getString("id"));
                member.setPasswd(rs.getString("passwd"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoindate(rs.getString("joindate"));
                list.add(member);
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public int memberDelete(int no) {
        String sql = "delete from membership where no='"+no+"';";
        int ret = 0;
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ret = ps.executeUpdate();

            con.close();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return ret;
    }


    /// TODO
    public int memberUpdate(MemberDTO dto) {
        System.out.println(dto.getNo());
        int ret = 0;
        String sql = "update membership set name='"+dto.getName()+"', email='"+dto.getEmail()+"', phone='"+dto.getPhone()+"', address='"+dto.getAddress()+"', passwd='"+dto.getPasswd()+"' where no='"+dto.getNo()+"';";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ret = ps.executeUpdate();

            con.close();
            ps.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ret;
    }

    public ArrayList <MemberDTO> getMemberList (String index, String value1){
        ArrayList <MemberDTO> list = new ArrayList <MemberDTO>();
        String sql = "select * from membership where "+index+" like '%"+value1+"%';";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setNo(rs.getInt("no"));
                member.setName(rs.getString("name"));
                member.setId(rs.getString("id"));
                member.setPasswd(rs.getString("passwd"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoindate(rs.getString("joindate"));
                list.add(member);
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList <MemberDTO> getMemberList (String index, String value1, String value2){
        ArrayList <MemberDTO> list = new ArrayList <MemberDTO>();
        String sql = "select * from membership where ("+index+"1 like '%"+value1+"%') and ("+index+"2 like '%"+value2+"%');";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setNo(rs.getInt("no"));
                member.setName(rs.getString("name"));
                member.setId(rs.getString("id"));
                member.setPasswd(rs.getString("passwd"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoindate(rs.getString("joindate"));
                list.add(member);
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList <MemberDTO> getMemberList (String index, String value1, String value2, String value3){
        ArrayList <MemberDTO> list = new ArrayList <MemberDTO>();
        String sql = "select * from membership where ("+index+"1 like '%"+value1+"%') and ("+index+"2 like '%"+value2+"%') and ("+index+"3 like '%"+value3+"%');";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setNo(rs.getInt("no"));
                member.setName(rs.getString("name"));
                member.setId(rs.getString("id"));
                member.setPasswd(rs.getString("passwd"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoindate(rs.getString("joindate"));
                list.add(member);
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public boolean checkId(String id, String passwd) {
        boolean ret = false;
        String sql = "select * from membership where (email = '"+id+"') and (passwd = '"+passwd+"');";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ret = rs.next();
            if(ret) {
                System.out.println("ID : "+id+" PASSWD : "+passwd);
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    public String getValue(String valueType, String value, String getType) {
        String ret = "";
        String sql = "select * from membership where "+valueType+"='"+value+"';";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                ret = rs.getString(getType);
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ret;
    }

}