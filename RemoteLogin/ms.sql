-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2021 年 06 月 24 日 12:30
-- 服务器版本: 5.5.20
-- PHP 版本: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `ms`
--

-- --------------------------------------------------------

--
-- 表的结构 `admin_user`
--

CREATE TABLE IF NOT EXISTS `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(20) CHARACTER SET gbk NOT NULL,
  `name` varchar(50) CHARACTER SET gbk NOT NULL,
  `phone_number` varchar(20) CHARACTER SET gbk NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `admin_user`
--

INSERT INTO `admin_user` (`id`, `PASSWORD`, `name`, `phone_number`) VALUES
(1, '123456', 'root', '111111'),
(2, '123456', 'root2', '222222');

-- --------------------------------------------------------

--
-- 表的结构 `board`
--

CREATE TABLE IF NOT EXISTS `board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) CHARACTER SET gbk NOT NULL,
  `content` varchar(120) CHARACTER SET gbk DEFAULT NULL,
  `publish_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- 转存表中的数据 `board`
--

INSERT INTO `board` (`id`, `title`, `content`, `publish_time`) VALUES
(3, '道路施工', '近期有多处道路施工，为各位住户带来不便之处，敬请谅解。', '2021-06-02 12:00:00'),
(4, '篮球比赛', '本周日下午3点将举行篮球比赛决赛，欢迎大家前来为队伍加油助威。', '2021-06-04 13:00:00'),
(5, '核酸检测', '请所有住户本周六到广场进行核酸检测，全员必须参与，谢谢配合。', '2021-06-11 09:00:00'),
(6, 'LOL夏季赛观赛活动', '6月18号RNG夏季赛首站对战OMG，将组织集中观看，现场位置有限，名额先到先得，大家踊跃报名。', '2021-06-16 12:00:00'),
(7, '防诈骗宣传', '近期各式诈骗信息、短信等频繁出现，各种新型诈骗形式层出不穷，请大家擦亮双眼，涉及到钱财的均不要相信，链接不要轻易打开，远离诈骗！', '2021-06-13 10:00:00'),
(8, '新冠疫苗', '为配合国家政策，助力对抗新冠疫情，请大家积极预约报名注射新冠疫苗，感谢！', '2021-06-13 11:00:00'),
(9, '缴纳5月费用', '请各位住户及时缴纳5月份的物业管理费、水电费，以便为大家吃鱼提供各项服务，截止日期6月18号。', '2021-06-08 09:00:00'),
(10, '文明社区建设', '7月份将迎来文明社区建设检验，请大家保持文明习惯，文明出行，爱护社区环境，为建设文明社区出一份力。', '2021-06-16 08:00:00'),
(11, '疫情消息', '近期国内仍有出现境内或境外输入病例，多地上升为中风险或高风险地区，请住户短期内不要前往这些地方，若有从中风险或高风险地区回来的住户，请及时报备！', '2021-06-15 11:00:00');

-- --------------------------------------------------------

--
-- 表的结构 `clean_info`
--

CREATE TABLE IF NOT EXISTS `clean_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clean_date` datetime DEFAULT NULL,
  `clean_machine_id` int(11) DEFAULT NULL,
  `clean_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- 转存表中的数据 `clean_info`
--

INSERT INTO `clean_info` (`id`, `clean_date`, `clean_machine_id`, `clean_user_id`) VALUES
(11, '2021-06-10 00:00:00', 111, 1000);

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `CONTENT` varchar(40) CHARACTER SET gbk NOT NULL,
  `board_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`id`, `comment_date`, `user_id`, `CONTENT`, `board_id`) VALUES
(5, '2021-06-02 15:08:36', 100, '又修路，天天修，艹！', 3),
(6, '2021-06-04 13:42:22', 100, '勇士总冠军！！！', 4),
(7, '2021-06-11 11:08:27', 100, '众志成城', 5),
(8, '2021-06-16 12:27:16', 100, '十年皇杂不请自来', 6),
(9, '2021-06-16 12:41:25', 111, '销户！！！！', 6),
(10, '2021-06-02 14:16:42', 111, '老深圳特色了', 3),
(11, '2021-06-13 15:20:30', 111, '只要我没钱就不会被骗', 7),
(13, '2021-06-16 13:15:39', 133, '有挂！', 6),
(14, '2021-06-13 12:22:34', 133, '预约不上啊', 8),
(15, '2021-06-16 12:41:32', 120, 'uzi人称小gala', 6),
(16, '2021-06-13 17:26:55', 120, '两针可以打不同的吗？？', 8),
(17, '2021-06-16 14:24:40', 116, '剑来', 6),
(18, '2021-06-15 17:15:16', 116, '已经戴好口罩~', 11),
(19, '2021-06-15 15:35:41', 100, 'niubi', 6);

-- --------------------------------------------------------

--
-- 表的结构 `info_clean_staff`
--

CREATE TABLE IF NOT EXISTS `info_clean_staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET gbk NOT NULL,
  `hire_date` datetime NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  `sex` varchar(40) CHARACTER SET gbk NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- 转存表中的数据 `info_clean_staff`
--

INSERT INTO `info_clean_staff` (`id`, `name`, `hire_date`, `phone_number`, `sex`) VALUES
(12, '阿莲', '2001-10-10 00:00:00', '13562899765', '女'),
(15, '晓琳', '2021-06-10 00:00:00', '13675328867', '女');

-- --------------------------------------------------------

--
-- 表的结构 `info_device`
--

CREATE TABLE IF NOT EXISTS `info_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_id` int(11) DEFAULT NULL,
  `type` varchar(40) CHARACTER SET gbk NOT NULL,
  `need_repair` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `info_device`
--

INSERT INTO `info_device` (`id`, `staff_id`, `type`, `need_repair`) VALUES
(1, 11, '空调', b'1'),
(2, 15, '水管', b'0'),
(3, 15, '天然气', b'0'),
(4, NULL, '热水器', b'0'),
(5, NULL, '无线宽带', b'0'),
(6, NULL, '电视机顶盒', b'0');

-- --------------------------------------------------------

--
-- 表的结构 `info_repair_staff`
--

CREATE TABLE IF NOT EXISTS `info_repair_staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avg_score` float DEFAULT NULL,
  `name` varchar(40) CHARACTER SET gbk DEFAULT NULL,
  `sex` varchar(1) CHARACTER SET gbk NOT NULL,
  `hire_date` datetime NOT NULL,
  `work_times` int(11) NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- 转存表中的数据 `info_repair_staff`
--

INSERT INTO `info_repair_staff` (`id`, `avg_score`, `name`, `sex`, `hire_date`, `work_times`, `phone_number`) VALUES
(9, 4.29469, '文武', '男', '2019-09-19 13:00:00', 236, '13823866361'),
(11, 4.0945, '杰克', '男', '2019-02-13 10:58:18', 109, '18877665543'),
(15, 4.0208, '小朝', '男', '2021-06-01 09:00:00', 11, '18768033216');

-- --------------------------------------------------------

--
-- 表的结构 `info_user`
--

CREATE TABLE IF NOT EXISTS `info_user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET gbk DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET gbk DEFAULT NULL,
  `room_number` varchar(20) CHARACTER SET gbk NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `total_pay` decimal(10,0) NOT NULL,
  `have_money` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=112 ;

--
-- 转存表中的数据 `info_user`
--

INSERT INTO `info_user` (`id`, `name`, `sex`, `room_number`, `phone_number`, `total_pay`, `have_money`) VALUES
(2, 'Lin', '女', '1234', '18835090152', '0', 50),
(100, 'Jason', '男', '1028', '13567678901', '0', 100.4),
(111, '张三', '男', '1028', '13789667531', '3400', 3065.8);

-- --------------------------------------------------------

--
-- 表的结构 `normal_user`
--

CREATE TABLE IF NOT EXISTS `normal_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(40) NOT NULL,
  `name` varchar(40) CHARACTER SET gbk NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=134 ;

--
-- 转存表中的数据 `normal_user`
--

INSERT INTO `normal_user` (`id`, `PASSWORD`, `name`, `user_id`) VALUES
(100, '100', 'Jason', 100),
(111, '111', '张三', 111),
(116, '116', '我好难', 116),
(120, '120', 'Lin', 120),
(133, '133', '波神', 133);

-- --------------------------------------------------------

--
-- 表的结构 `post_cost`
--

CREATE TABLE IF NOT EXISTS `post_cost` (
  `post_cost_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_user_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `water_cost` decimal(10,0) DEFAULT NULL,
  `elect_cost` decimal(10,0) DEFAULT NULL,
  `done` bit(1) DEFAULT NULL,
  `should_pay` decimal(10,0) NOT NULL,
  `payed` decimal(10,0) NOT NULL,
  `unpay` decimal(10,0) NOT NULL,
  PRIMARY KEY (`post_cost_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `post_cost`
--

INSERT INTO `post_cost` (`post_cost_id`, `post_user_id`, `date`, `water_cost`, `elect_cost`, `done`, `should_pay`, `payed`, `unpay`) VALUES
(1, 100, '2021-06-09 00:00:00', '50', '200', b'0', '250', '40', '100');

-- --------------------------------------------------------

--
-- 表的结构 `report_for_repair`
--

CREATE TABLE IF NOT EXISTS `report_for_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_date` datetime NOT NULL,
  `device_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `repaired` bit(1) NOT NULL,
  `finish_date` datetime DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- 转存表中的数据 `report_for_repair`
--

INSERT INTO `report_for_repair` (`id`, `report_date`, `device_id`, `user_id`, `repaired`, `finish_date`, `staff_id`, `score`) VALUES
(1, '2021-06-13 08:37:19', 1, 111, b'1', '2021-06-13 12:32:28', 11, 3.5),
(7, '2021-06-16 00:03:32', 2, 111, b'1', '2021-06-16 00:09:38', 9, 4.5),
(8, '2021-06-16 00:11:04', 3, 111, b'1', '2021-06-16 00:13:44', 15, 5),
(9, '2021-06-16 00:15:20', 2, 111, b'1', '2021-06-16 00:16:03', 15, 5);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
