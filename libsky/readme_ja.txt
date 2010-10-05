Skype4Java 修正パッチ

概要：
　Skype4Java (http://skype.sourceforge.jp/) は、久納孝治氏によって作成された、Skype 2.0 API を使うための Java クラスライブラリです。
本修正パッチは、Skype4Java ライブラリを MacOSX 10.6 (SnowLeopard) の上で再コンパイルしたものです。

　現時点（2009.10.25) で公開されているバイナリパッケージ (http://skype.sourceforge.jp/) は、内容が古く、JSAにより通信処理が書かれて
いたため、skype.framework を用いているバージョンのバイナリを作成しました。


ライセンス：
　本パッチのライセンスには Apacahe2.0 もしくは Eclipse Public License 1.0 が適用されます。


ファイル説明：
　libskype.jnlib ... MacOSX 用 JNI ライブラリ (x86)
　libskype.so ... Linux 用 JNI ライブラリ (x86)
　libskype.dll ... Windows 用 JNI ライブラリ (x86)
　skype.jar ... Java クラスファイルパッケージ


連絡先：
　本パッチに関する質問・バグ報告等は、井上隆広（hinata@coins.tsukuba.ac.jp）までお願いいたします。
オリジナルの作者である久納孝治氏には、決して連絡しないようにして下さい。


謝辞：
　Skype4Java を開発して下さった久納孝治氏に感謝いたします。


履歴：
　2009.10.25 パッチ公開


----
井上　隆広
　E-Mail: hinata@coins.tsukuba.ac.jp
　Blog: http://www.hinatablog.org/?tag=skype
----

