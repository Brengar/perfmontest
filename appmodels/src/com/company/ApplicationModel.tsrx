<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>ApplicationModel</className>
    <objects>
        <object type="Window" technology="Standard Windows">
            <name>SysMon</name>
            <codeName>mainwindow</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="IsChildWindow" type="boolean" isRegExp="false">false</property>
                    <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                    <property name="WindowClassRegExp" type="string" isRegExp="false">MMCMainFrame</property>
                    <property name="WindowTitleRegExp" type="string" isRegExp="false">Системный монитор</property>
                </properties>
            </identification>
            <children>
                <object type="Window" technology="Standard Windows">
                    <name>Perf</name>
                    <codeName>window</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="WindowClassRegExp" type="string" isRegExp="false">MMCChildFrm</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Производительность</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="TreeView" technology="Standard Windows">
                            <name>SysTreeView32</name>
                            <codeName>sysTreeView32TreeView</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="NativeClass" type="string" isRegExp="false">SysTreeView32</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="ToolBar" technology="Standard Windows">
                    <name>ToolbarWindow32</name>
                    <codeName>toolbarWindow32ToolBar</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="NativeClass" type="string" isRegExp="false">ToolbarWindow32</property>
                            <property name="Text" type="string" isRegExp="false"></property>
                            <property name="WindowId" type="int" isRegExp="false">4098</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
