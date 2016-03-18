<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/libs/jquery.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/home.js"></script>
    <script src="js/login.js"></script>
  </head>
  <body>
    <html>
      <body>
        <script>
          $(function () {
            if (B.ie7 || B.ie8 || B.ie9) {
              placeHolderIE();
            }
            $(".login .loginBtn").dialog({
              width: 700,
              content: '<div class="dialog-login"><div class="login">' +
              '<label class="fundAccount clearfix"><div class="login-err">资金账号或密码错误</div><input class="input" id="fundAccoundInput" type="text" placeholder="请输入资金账号"></label>' +
              '<label class="password clearfix"></span><input class="input" type="password" placeholder="请输入交易密码"></label>' +
              '<label for=""><input type="text" class="veri_code_input" placeholder="请输入验证码"><img src="images/veri-code.png" alt=""></label>' +
              '<a href="javascript:void(0)" onclick="login()" class="loginBtn">登录</a></div>' +
              '<div class="ai-open"><p class="tip">如果您还不是开源用户，请</p><div class="open-btn">马上开户</div></div>' +
              '</div>',
              afterShow: function () {
                var that = this;
                $('.loginBtn').click(function () {
                  $('.login-err').show();
                });
                $('.input').focus(function () {
                  $('.login-err').hide();
                });
                if (B.ie7 || B.ie8 || B.ie9) {
                  placeHolderIE();
                }
              }
            })
          })
          
        </script>
        <div class="ui-header">
          <div class="header-inner">
            <div class="logo"><a href="/"><img src="images/logo.png" alt="logo"></a></div>
            <div class="nav">
              <ul>
                <li class="nav-item"><a class="nav-title active">首页</a></li>
                <li class="nav-item"><a class="nav-title">办业务</a></li>
                <li class="nav-item"><a class="nav-title">买理财</a></li>
                <li class="nav-item"><a class="nav-title">找资源</a></li>
                <li class="nav-item"><a class="nav-title">开心贷</a></li>
                <li class="nav-item"><a class="nav-title">看行情</a></li>
                <li class="nav-item"><a class="nav-title">下软件</a></li>
              </ul>
            </div>
            <div class="user-cen"><span class="nav-title user-center"></span>
              <ul class="sub login">
                <li class="sublist loginBtn">我的资料</li>
                <li class="sublist loginBtn">我的资金</li>
                <li class="sublist loginBtn">我的股票</li>
                <li class="sublist loginBtn">我的订单</li>
                <li class="sublist loginBtn">我的账单</li>
                <li class="sublist loginBtn">资金流水</li>
                <li class="sublist loginBtn">消息中心</li>
                <li class="sublist">退出</li>
              </ul>
            </div>
          </div>
        </div>
      </body>
    </html>
    <!--include includes/login-->
    <div class="home">
      <div class="fix-side-bar">
        <div class="menu fix-top">
          <div class="icon"></div>
          <div class="des">顶部</div>
        </div>
        <div class="menu fix-share">
          <div class="icon"></div>
          <div class="des">分享</div>
        </div>
        <div class="menu fix-que">
          <div class="icon"></div>
          <div class="des">问题</div>
        </div>
        <div class="menu fix-user">
          <div class="icon"></div>
          <div class="des">个人</div>
        </div>
        <div class="menu fix-edit">
          <div class="icon"></div>
          <div class="des">编辑</div>
        </div>
        <div class="menu fix-qrcode">
          <div class="icon"></div>
          <div class="des">扫描</div>
        </div>
      </div>
      <div class="banner">
        <div class="content-wrap">
          <div class="left-menu">
            <div class="cate-wrap">
              <div class="cate-title-list">
                <div class="cate-m fore1">
                  <div class="cate-mt">
                    <h2 class="title"><a class="text">办业务</a><a class="arrow"></a></h2>
                    <div class="extra"><a>创业板转签</a><a>银证转账</a></div><span class="angle"></span>
                  </div>
                </div>
                <div class="cate-m fore2">
                  <div class="cate-mt">
                    <h2 class="title"><a class="text">买理财</a><a class="arrow"></a></h2>
                    <div class="extra"><a>开源华山1号</a><a>开源秦岭1号</a></div><span class="angle"></span>
                  </div>
                </div>
                <div class="cate-m fore3">
                  <div class="cate-mt">
                    <h2 class="title"><a class="text">找咨讯</a><a class="arrow"></a></h2>
                    <div class="extra"><a>业务公告</a><a>财经要闻</a></div><span class="angle"></span>
                  </div>
                </div>
                <div class="cate-m fore4">
                  <div class="cate-mt">
                    <h2 class="title"><a class="text">开心贷</a><a class="arrow"></a></h2>
                    <div class="extra"><a>融资打新</a><a>融资可用</a></div><span class="angle"></span>
                  </div>
                </div>
                <div class="cate-m fore5">
                  <div class="cate-mt">
                    <h2 class="title"><a class="text">下软件</a><span class="arrow"></span></h2>
                    <div class="extra"></div><span class="angle"></span>
                  </div>
                </div>
              </div>
              <div class="cate-content-list">
                <div class="cate-mc fore1">
                  <div class="title"><span class="text">办业务</span><a target="_blank" href="http://licai.jd.com/" clstag="jr|keycount|jr_index|nav_lv3_lc_more" class="more">更多<i></i></a></div>
                  <div class="goods-list"><a target="_blank" href="#">开放式基金</a><a target="_blank" href="#">融资融券</a><a target="_blank" href="#">OTC</a><a target="_blank" href="#">创业板转签</a><a target="_blank" href="#">港股通</a><a target="_blank" href="#">风险警示版</a></div>
                  <div class="puzzle-item">
                    <h1>开户送好礼</h1>
                    <h3>立享超低佣金</h3>
                    <div class="kh-btn">立即开户</div>
                  </div>
                  <div class="goods-extra">
                    <div class="content"></div>
                  </div>
                </div>
                <div class="cate-mc fore2">
                  <div class="title"><span class="text">买理财</span><a target="_blank" href="http://z.jd.com/" clstag="jr|keycount|jr_index|nav_lv3_zz_more" class="more">更多<i></i></a></div>
                  <div class="goods-list"><a target="_blank" href="#">集合资产管理计划</a><a target="_blank" href="#">基金公司专户产品</a><a target="_blank" href="#">私募基金产品</a><a target="_blank" href="#">公募基金产品</a><a target="_blank" href="#">信托类</a><a target="_blank" href="#">收益凭证</a></div>
                  <div class="puzzle-item">
                    <h1>开源华山1号</h1>
                    <h3>预计年化收益率<span class="num">6%</span>起</h3><a class="kh-btn">立即开户</a>
                  </div>
                </div>
                <div class="cate-mc fore3">
                  <div class="title"><span class="text">找资讯</span><a target="_blank" href="http://bao.jd.com/" clstag="jr|keycount|jr_index|nav_lv3_bx_more" class="more">更多<i></i></a></div>
                  <div class="goods-list billboard"><a target="_blank" href="#" class="right"><span class="board-title">业务公告</span><span class="board-content">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</span></a><a target="_blank" href="#"><span class="board-title">业务公告</span><span class="board-content">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</span></a><a target="_blank" href="#" class="right"><span class="board-title">业务公告</span><span class="board-content">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</span></a><a target="_blank" href="#"><span class="board-title">业务公告</span><span class="board-content">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</span></a></div>
                </div>
                <div class="cate-mc fore4">
                  <div class="title"><span class="text">开心贷</span><a target="_blank" href="#" class="more">更多<i></i></a></div>
                  <div class="goods-list"><a target="_blank" href="#">融资打新</a><a target="_blank" href="#">融资炒股</a><a target="_blank" href="#">融资取现</a></div>
                  <div class="puzzle-item">
                    <h1>新股哪里逃</h1>
                    <h3>花券商的钱去打新</h3><a class="kh-btn">马上申购新股</a>
                  </div>
                </div>
                <div class="cate-mc fore5">
                  <div class="title"><span class="text">下软件</span></div>
                  <div class="goods-list down-list"><a target="_blank" href="#">开源证券同花顺网上行情分析及交易系统</a><a target="_blank" href="#">开源证券通达信网上行情分析及交易系统</a><a target="_blank" href="#">开源证券钱龙网上行情分析及交易系统（金典版）</a><a target="_blank" href="#">开源证券钱龙网上行情分析及交易系统（金典版）</a><a target="_blank" href="#">开源证券钱龙网上行情分析及交易系统（金典版）</a><a target="_blank" href="#">开源证券钱龙网上行情分析及交易系统（金典版）</a><a target="_blank" href="#">开源证券钱龙网上行情分析及交易系统（金典版）</a><a target="_blank" href="#">开源证券钱龙网上行情分析及交易系统（金典版）</a></div>
                  <div class="goods-promotion qrcode">
                    <p class="qr-t">扫一扫</p><img src="images/qrcode.png" class="qr-img">
                    <p class="qr-d">下载行情交易客户端</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="right-menu">
            <div class="puzzle-item item-1">
              <h1>开户送好礼</h1>
              <h3>立享超低佣金</h3><a class="kh-btn">立即开户</a>
            </div>
            <div class="puzzle-item item-2">
              <h3>扫一扫</h3><img src="images/qrcode.png" class="qrcode">
              <h4>加微信公众号</h4>
            </div>
          </div>
        </div>
      </div>
      <div class="content-wrap">
        <!--多快好省-->
        <div class="cate-cards">
          <div class="cate-card">
            <div class="cate-mask">
              <div class="cate-mask-title">介绍内容</div>
              <div class="cate-mask-content">内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容</div>
            </div><a class="cate-img duo"></a><a class="cate-des">丰富的理财产品</a>
          </div>
          <div class="cate-card">
            <div class="cate-mask">
              <div class="cate-mask-title"></div>
              <div class="cate-mask-content"></div>
            </div><a class="cate-img kuai"></a><a class="cate-des">在线业务极速办理</a>
          </div>
          <div class="cate-card">
            <div class="cate-mask">
              <div class="cate-mask-title"></div>
              <div class="cate-mask-content"></div>
            </div><a class="cate-img hao"></a><a class="cate-des">中国创新型券商</a>
          </div>
          <div class="cate-card last">
            <div class="cate-mask">
              <div class="cate-mask-title"></div>
              <div class="cate-mask-content"></div>
            </div><a class="cate-img sheng"></a><a class="cate-des">2.5%超低佣金</a>
          </div>
        </div>
        <!--找业务-->
        <div class="floor yw">
          <div class="floor-side">
            <div class="fs-title">办业务</div>
            <div class="fs-tabs">
              <ul>
                <li class="active"><a href="" target="_blank">账户类</a></li>
                <li><a href="" target="_blank">权限类</a></li>
                <li><a href="" target="_blank">其他</a></li>
              </ul>
            </div>
          </div>
          <div class="floor-content">
            <div class="fc-inner">
              <div class="floor-section floor-section-1 active"><a class="card">证券账户</a><a class="card">开放式基金</a><a class="card">OTC</a><a class="card last">融资融券</a><a class="card">证券账户</a><a class="card">开放式基金</a><a class="card">OTC</a><a class="card last">融资融券</a></div>
              <div class="floor-section floor-section-1"><a class="card">证券账户</a><a class="card">开放式基金</a><a class="card">OTC</a><a class="card">证券账户</a></div>
            </div>
          </div>
        </div>
        <!--理财-->
        <div class="floor lc">
          <div class="floor-side">
            <div class="fs-title">买理财</div>
            <div class="fs-tabs">
              <ul>
                <li class="active"><a href="" target="_blank">集合资产管理计划</a></li>
                <li><a href="" target="_blank">基金公司专户产品</a></li>
                <li><a href="" target="_blank">私募基金产品</a></li>
                <li><a href="" target="_blank">收益凭证</a></li>
                <li><a href="">信托类</a></li>
              </ul>
            </div>
          </div>
          <div class="floor-content">
            <div class="fc-inner">
              <div class="floor-section floor-section-1 active">
                <div class="fc-left">
                  <h2>理财宝</h2>
                  <div class="line nomargin">预期年化收益率</div>
                  <div class="line num">6.00%</div>
                  <div class="line gm"><span class="line-left">购买起点</span><span class="line-right">100万元</span></div><a class="btn">立即购买</a>
                </div>
                <div class="fc-right">
                  <div class="fc-right-card">
                    <h3>理财宝</h3>
                    <div class="line"><span class="line-left">预期年华收益率</span><span class="line-right">购买起点</span></div>
                    <div class="line"><span class="line-left num">4.50%</span><span class="line-right">100万元</span></div>
                  </div>
                  <div class="fc-right-card">
                    <h3>理财宝</h3>
                    <div class="line"><span class="line-left">预期年华收益率</span><span class="line-right">购买起点</span></div>
                    <div class="line"><span class="line-left num">4.50%</span><span class="line-right">100万元</span></div>
                  </div>
                  <div class="fc-right-card">
                    <h3>理财宝</h3>
                    <div class="line"><span class="line-left">预期年华收益率</span><span class="line-right">购买起点</span></div>
                    <div class="line"><span class="line-left num">4.50%</span><span class="line-right">100万元</span></div>
                  </div>
                  <div class="fc-right-card">
                    <h3>理财宝</h3>
                    <div class="line"><span class="line-left">预期年华收益率</span><span class="line-right">购买起点</span></div>
                    <div class="line"><span class="line-left num">4.50%</span><span class="line-right">100万元</span></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--资讯-->
        <div class="floor zx">
          <div class="floor-side">
            <div class="fs-title">找资讯</div>
            <div class="fs-tabs">
              <ul>
                <li class="active"><a href="" target="_blank">业务公告</a></li>
                <li><a href="" target="_blank">上市公司</a></li>
                <li><a href="" target="_blank">财经要闻</a></li>
                <li><a href="" target="_blank">基金债券</a></li>
                <li><a href="">股指期货</a></li>
                <li><a href="">港股咨询</a></li>
              </ul>
            </div>
          </div>
          <div class="floor-content">
            <div class="fc-inner">
              <div class="floor-section floor-section-1 active">
                <div class="nav-bar"><a class="btn nav-btn">分类一</a><a class="btn nav-btn">分类二</a><a class="btn nav-btn">分类三</a><a class="btn nav-btn">分类四</a><a class="btn nav-btn">分类五</a><a class="btn nav-btn">分类六</a><a class="btn add-btn">+</a></div>
                <div class="zx-lists"><a class="zx-list"><span class="zx-lf"> 关于贯彻落实《关于清理整顿违法从事证券业务活动的</span><span class="zx-rf"> 2015年9月9日</span></a><a class="zx-list"><span class="zx-lf"> 关于贯彻落实《关于清理整顿违法从事证券业务活动的</span><span class="zx-rf"> 2015年9月9日</span></a><a class="zx-list"><span class="zx-lf"> 关于贯彻落实《关于清理整顿违法从事证券业务活动的关于贯彻落实《关于清理整顿违法从事证券业务活动的关于贯彻落实《关于清理整顿违法从事证券业务活动的</span><span class="zx-rf"> 2015年9月9日</span></a><a class="zx-list"><span class="zx-lf"> 关于贯彻落实《关于清理整顿违法从事证券业务活动的</span><span class="zx-rf"> 2015年9月9日</span></a></div><a class="next-btn"></a>
              </div>
            </div>
          </div>
        </div>
        <!--开心贷-->
        <div class="floor kxd">
          <div class="floor-side">
            <div class="fs-title">开心贷</div>
            <div class="fs-tabs">
              <ul>
                <li class="active"><a href="" target="_blank">借款</a></li>
                <li><a href="" target="_blank">打新股</a></li>
                <li><a href="" target="_blank">其他</a></li>
              </ul>
            </div>
          </div>
          <div class="floor-content">
            <div class="fc-inner">
              <div class="floor-section floor-section-1 active">
                <div class="kxd-posts">
                  <h3>融资打新</h3>
                  <h4>业务介绍</h4>
                  <div class="kxdj-posts-content"> 收益率2.155%，暂无法成交，青等待货重新下单收益率2.155%，暂无法成交，青等待货重新下单收益率2.155%，暂无法成交，青等待货重新下单</div><a class="btn kxd-btn">马上开通</a>
                </div>
                <div class="kxd-posts">
                  <h3>融资打新</h3>
                  <h4>业务介绍</h4>
                  <div class="kxdj-posts-content"> 收益率2.155%，暂无法成交，青等待货重新下单收益率2.155%，暂无法成交，青等待货重新下单收益率2.155%，暂无法成交，青等待货重新下单</div><a class="btn kxd-btn">马上开通</a>
                </div>
                <div class="kxd-posts last">
                  <h3>融资打新</h3>
                  <h4>业务介绍</h4>
                  <div class="kxdj-posts-content"> 收益率2.155%，暂无法成交，青等待货重新下单收益率2.155%，暂无法成交，青等待货重新下单收益率2.155%，暂无法成交，青等待货重新下单</div><a class="btn kxd-btn">马上开通</a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--下软件-->
        <div class="floor rj">
          <div class="floor-side">
            <div class="fs-title">下软件</div>
            <div class="fs-tabs">
              <ul>
                <li class="active"><a href="" target="_blank">手机APP</a></li>
                <li><a href="" target="_blank">行情交易</a></li>
                <li><a href="" target="_blank">股票期权</a></li>
                <li><a href="" target="_blank">其他期权</a></li>
              </ul>
            </div>
          </div>
          <div class="floor-content">
            <div class="fc-inner">
              <div class="floor-section floor-section-1 active">
                <div class="rj-left">
                  <p class="qr-t">扫一扫</p><img src="images/qrcode.png" class="qr-img">
                  <p class="qr-d">下载行情交易客户端</p>
                </div>
                <div class="rj-right">
                  <h3>介绍</h3>
                  <div class="fj-content">
                    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <html>
      <body>
        <div class="ui-footer">
          <div class="footer-top">
            <dl class="footer-dl">
              <dt>走进开源</dt>
              <dd><a href="">公司简介</a></dd>
              <dd><a href="">营业网点</a></dd>
              <dd><a href="">组织机构</a></dd>
              <dd><a href="">公司动态</a></dd>
              <dd><a href="">招聘信息</a></dd>
            </dl>
            <dl class="footer-dl">
              <dt>投资者教育</dt>
              <dd><a href="">投资规则</a></dd>
              <dd><a href="">证券知识</a></dd>
              <dd><a href="">交易知识</a></dd>
              <dd><a href="">风险提示</a></dd>
              <dd><a href="">打击非法证券</a></dd>
              <dd><a href="">法律法规</a></dd>
              <dd><a href="">股票期权专栏</a></dd>
            </dl>
            <dl class="footer-dl">
              <dt>公告与提示</dt>
              <dd><a href="">免责声明</a></dd>
              <dd><a href="">人员公告</a></dd>
              <dd><a href="">常见问题</a></dd>
              <dd><a href="">问卷调查</a></dd>
            </dl>
            <dl class="footer-dl">
              <dt>友情链接</dt>
              <dd><a href="">中国证券业协会</a></dd>
              <dd><a href="">中国证监会</a></dd>
              <dd><a href="">上交所投资者教育</a></dd>
              <dd><a href="">上交所投资者教育</a></dd>
            </dl>
            <dl class="footer-dl">
              <dt>客服电话</dt>
              <dd class="phone-num"> 400-860-8866</dd>
            </dl>
          </div>
          <div class="footer-bottom-wrap">
            <div class="footer-bottom">
              <div class="p">
                <p>开源证券有限责任公司版权所有 Copyright©2000-2013 All Rights 陕ICP备05002298号</p>
              </div>
              <div class="p">
                <p>本站所提供的信息仅供参考，本公司开展网上证券委托业务已经中国证券监督委员会核准;</p>
                <p>该核准不构成中国证券监督管理委员会对本公司网上证券委托业务安全和效率的任何保证</p>
              </div>
            </div>
          </div>
        </div>
      </body>
    </html>
  </body>
</html>