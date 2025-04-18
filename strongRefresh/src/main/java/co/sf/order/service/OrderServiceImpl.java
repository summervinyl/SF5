package co.sf.order.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import co.sf.common.DataSource;
import co.sf.order.mapper.OrderMapper;
import co.sf.order.vo.OrderDetailVO;
import co.sf.order.vo.OrderVO;

public class OrderServiceImpl implements OrderService {
    SqlSessionFactory sqlSessionFactory = DataSource.getInstance();
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

    @Override
    public List<OrderDetailVO> getOrderDetails(String id) {
        return mapper.orderDetailList(id);
    }

    @Override
    public boolean addAddress(OrderVO ovo) {
        return mapper.addAddress(ovo) == 1;
    }

    @Override
    public List<OrderDetailVO> OrderDetailList(String orderCode) {
        return mapper.orderDetailList(orderCode);
    }

    @Override
    public boolean removeSelectedCartItems(Map<String, Object> params) {
        return mapper.removeSelectedCartItems(params) == 1;
    }

    @Override
    public boolean updateOrderStatus(Map<String, Object> params) {
        return mapper.updateOrderStatus(params) == 1;
    }
}