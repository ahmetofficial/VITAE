/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('PHOTOS', {
    photo_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    description: {
      type: DataTypes.STRING,
      allowNull: false
    },
    created_time: {
      type: DataTypes.DATE,
      allowNull: false
    },
    owner_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    location_path: {
      type: DataTypes.STRING,
      allowNull: false
    },
    subject_id: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    }
  }, {
    tableName: 'PHOTOS'
  });
};
