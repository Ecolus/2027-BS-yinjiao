# 文献候选清单（核验进度）

> 来源：曾维老师《技术方案与参考文献建议》里给尹骄的 17 条候选 + 4 个检索方向。
> 状态说明：
> - 已核验通过（2026-09-27 逐条检索确认存在性与出处）→ 可进 verified_references.md
> - 待补细节（存在性已确认，卷期页码/DOI 待补）
> - 不采用（与课题相关度低或重复）

## 一、老师给的候选（核验结果）

| # | 候选条目 | 核验状态 | 筛选建议 |
|---|---|---|---|
| 1 | OASIS, 2019. MQTT version 5.0[S/OL]. OASIS Standard. | 确认为 OASIS 标准（MQTT 5.0，2019-03 发布） | 保留（MQTT 协议标准，必引） |
| 2 | FIELDING R T, RESCHKE J, 2014. HTTP/1.1 message syntax and routing[S/OL]. RFC 7230. | 确认真实（RFC 7230，2014-06） | 保留（HTTP 标准，必引） |
| 3 | NAIK N, 2017. Choice of effective messaging protocols for IoT systems: MQTT, CoAP, AMQP and HTTP[C]//IEEE ISSE. | 确认真实（IEEE ISSE 2017） | 保留 + 精读（协议选型核心文献） |
| 4 | THANGAVEL D, et al., 2014. Performance evaluation of MQTT and CoAP via a common middleware[C]//IEEE ISSNIP. | 确认真实（ISSNIP 2014，作者 5 人） | 保留 + 精读（性能评估方法直接参考） |
| 5 | LIGHT R A, 2017. Mosquitto: server and client implementation of the MQTT protocol[J]. JOSS. | 确认真实（Journal of Open Source Software） | 保留 + 精读（用的 broker 的官方出处） |
| 6 | SHELBY Z, HARTKE K, BORMANN C, 2014. CoAP[S/OL]. RFC 7252. | 确认真实（RFC 7252，2014-06） | 保留（作对比参照，可选） |
| 7 | FIELDING R T, 2000. Architectural styles and the design of network-based software architectures[D]. UC Irvine. | 确认真实（博士论文） | 保留（REST 架构经典，论文理论支撑） |
| 8 | AL-FUQAHA A, et al., 2015. Internet of Things: a survey on enabling technologies, protocols, and applications[J]. IEEE COMST. | 确认真实（作者 5 人） | 保留 + 精读（IoT 综述，背景章节） |
| 9 | ATZORI L, IERA A, MORABITO G, 2010. The Internet of Things: a survey[J]. Computer Networks. | 确认真实（作者 3 人吻合） | 保留（IoT 经典综述，背景） |
| 10 | GUBBI J, et al., 2013. Internet of Things (IoT): a vision, architectural elements, and future directions[J]. FGCS. | 确认真实（实际 4 位作者，FGCS 29(7)） | 保留（IoT 综述，背景） |
| 11 | YASSEIN M B, SHATNAWI M Q, AL-ZOUBI D, 2016. Application layer protocols for the Internet of Things: a survey[C]//ICEMIS. | 确认真实存在（题名/作者吻合，ICEMIS 2016） | 保留（应用层协议综述） |
| 12 | DIZDAREVIĆ J, et al., 2019. A survey of communication protocols for Internet of Things...[J]. ACM Computing Surveys. | 确认真实（ACM CSUR 正式版） | 保留 + 精读（通信协议综述，含性能问题） |
| 13 | STANKOVIC J A, 2014. Research directions for the Internet of Things[J]. IEEE IoT Journal. | 确认真实 | 保留（研究方向，可选） |
| 14 | GOOGLE, 2024. Android developers documentation[EB/OL]. | 网页文档，已通过国内镜像确认可访问 | 保留（引 App 实现时用） |
| 15 | ESPRESSIF SYSTEMS, 2024. ESP8266 SDK documentation[EB/OL]. | 网页文档，已确认可访问 | 保留（设备端引用） |
| 16 | PATHAK A, HU Y C, ZHANG M, 2012. Where is the energy spent inside my app?[C]//EuroSys. | 确认真实（EuroSys 2012） | 保留（拓展指标-手机耗电的理论支撑） |
| 17 | SHELBY Z, 2014. CoAP: The application layer for the IoT | 与 #6 重复，不单列 | 删除（同一 RFC 7252） |

筛选结论：17 条保留 15 条（删除重复的第 17 条），全部核验通过；其中 6 篇标为精读优先。

## 二、4 个检索方向搜出的中文文献（8 条全部核验完成）

| # | 检索方向 | 著录（GB/T 7714-2015） | 核验情况 |
|---|---|---|---|
| 中1 | 方向1：MQTT HTTP 对比 物联网 性能 | 周超, 陈建辉, 骆绍烨. 物联网环境下HTTP与MQTT通讯协议比较探究[J]. 莆田学院学报, 2017, 24(5): 57-60. | 知网详情页确认 |
| 中2 | 方向1 | 龚永罡, 付俊英, 汪昕宇, 等. MQTT协议在物联网中的应用研究[J]. 电脑与电信, 2017(11): 89-91, 94. | 知网详情页确认，含 DOI |
| 中3 | 方向2：移动端 远程控制 物联网 App 设计 | 李慧, 刘星桥, 李景, 等. 基于物联网Android平台的水产养殖远程监控系统[J]. 农业工程学报, 2013, 29(13): 175-181. | 知网详情页确认 |
| 中4 | 方向2 | 李光明, 孙英爽, 党小娟. 基于安卓的远程监控系统的设计与实现[J]. 计算机工程与设计, 2016, 37(2): 556-561. | 知网详情页确认，含 DOI |
| 中5 | 方向2/4 | 李国利, 周创, 牟福元. 基于ESP32的温室大棚环境远程监控系统设计[J]. 中国农机化学报, 2022, 43(3): 47-52. | 知网详情页确认，含 DOI |
| 中6 | 方向3：弱网 重连 消息可靠性 移动应用 | 侯敏, 刘倩, 杨华勇, 等. 基于MQTT协议的海洋观测数据推送系统[J]. 计算机工程与应用, 2019, 55(20): 227-231. | 知网详情页确认 |
| 中7 | 方向3 | 李鹏程, 张文胜, 郭栋, 等. 基于物联网通信协议的车辆信息系统开发[J]. 计算机工程与设计, 2022, 43(3): 646-653. | 知网详情页确认，含 DOI |
| 中8 | 方向4：环境监测 系统 Android 实现 | 高萌萌, 孙志刚, 李硕, 等. 基于NB-IoT的区域空气质量监测系统设计与实现[J]. 计算机测量与控制, 2020, 28(10): 55-59. | 维普/杂志官网目录/国家平台三处确认（知网未收录该篇，不影响） |

说明：中8 知网没有收录，但《计算机测量与控制》杂志官网 2020 年第 10 期目录、维普收录页、国家科研论文平台引用都确认了它的出处，著录有效。

## 三、精读计划（≥5 篇，阅读卡见 reading_notes/）

- 阅读卡模板已建（reading_notes/）
- 精读 1：NAIK 2017（协议选型）
- 精读 2：THANGAVEL 2014（性能评估方法）
- 精读 3：LIGHT 2017（Mosquitto）
- 精读 4：DIZDAREVIĆ 2019（通信协议综述）
- 精读 5：AL-FUQAHA 2015（IoT 综述）
