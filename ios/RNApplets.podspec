
Pod::Spec.new do |s|
  s.name         = "RNApplets"
  s.version      = "1.0.0"
  s.summary      = "RNApplets"
  s.description  = <<-DESC
                  RNApplets
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "hozan" => "1033682873@qq.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/fengandrain/react-native-jump-applets.git", :tag => "master" }
  s.source_files  = "**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.vendored_libraries = "libWeChatSDK.a"
  s.ios.frameworks = 'SystemConfiguration','CoreTelephony','XCTest'
  s.ios.library = 'sqlite3','c++','z'
  #s.dependency "others"

end

  